package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.PropertyValue;
import com.niuwenyu.springframework.beans.PropertyValues;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;
import com.niuwenyu.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author wenyuniu
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

//    @Override
//    Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
//        return createBean(beanName, beanDefinition, (Object[]) null);
//    }

    @Override
    Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition, beanName, args);
            if(beanDefinition.getPropertyValues() != null){
                applyPropertyValues(beanName, bean, beanDefinition);
            }
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        return addSingleton(beanName, bean);
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


}
