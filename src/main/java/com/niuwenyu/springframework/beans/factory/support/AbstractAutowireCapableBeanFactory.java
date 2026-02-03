package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.PropertyValue;
import com.niuwenyu.springframework.beans.PropertyValues;
import com.niuwenyu.springframework.beans.factory.BeanFactory;
import com.niuwenyu.springframework.beans.factory.config.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wenyuniu
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    @Override
    Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition, beanName, args);
            if(beanDefinition.getPropertyValues() != null){
                applyPropertyValues(beanName, bean, beanDefinition);
            }
            bean = initializeBean(beanName, bean, beanDefinition);
            registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        return addSingleton(beanName, bean);
    }

    public void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition){
        if(bean instanceof DisposableBean){
            addDisposableBean(beanName, (DisposableBean) bean);
        }else{
            addDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition.getDestoryMethod()));
        }
    }


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        bean = invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) {
        Object result = bean;
        for(BeanPostProcessor beanPostProcessor: getBeanPostProcessors()){
            Object current = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            if(current == null){
                return result;
            }
            result = current;
        }

        return result;
    }

    private Object invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        String initMethodName = beanDefinition.getInitMethod();
        if(initMethodName != null && initMethodName.length() > 0){
            try {
                Method initMethod = wrappedBean.getClass().getSuperclass().getMethod(initMethodName);
                initMethod.invoke(wrappedBean);
            }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return wrappedBean;
    }

    private Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        for(BeanPostProcessor beanPostProcessor: getBeanPostProcessors()){
            Object current = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            if(current == null){
                return result;
            }
            result = current;
        }

        return result;
    }


    Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException {
        Constructor<?> constructorToUse = null;
        if(args != null){
            Class<?>[] classes = new Class[args.length];
            for(int i = 0; i < args.length; i++){
                classes[i] = args[i].getClass();
            }
            try {
                constructorToUse = beanDefinition.getBeanClass().getConstructor(classes);
                return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
            } catch (NoSuchMethodException e) {
                throw new BeansException("没有这个构造器啊");

            }
        }else{
            return getInstantiationStrategy().instantiate(beanDefinition, beanName, null, null);
        }

    }

    /**
     * 使用反射填充cglib生成的对象时，需要拿到该对象的父类，不然会报noSuchField
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @throws BeansException
     */

    void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for(PropertyValue propertyValue: propertyValues.getPropertyValues()){
            try {
                Field field = bean.getClass().getSuperclass().getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                if(propertyValue.getValue() instanceof BeanReference){
                    field.set(bean, getBean(((BeanReference) propertyValue.getValue()).getBeanName()));
                }else{
                    field.set(bean, propertyValue.getValue());
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new BeansException(e.toString());
            }

        }
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        beanPostProcessorList.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return beanPostProcessorList;
    }


}
