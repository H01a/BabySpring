package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

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
                throw new BeansException("没有这个构造期啊");
            }
        }else{
            return getInstantiationStrategy().instantiate(beanDefinition, beanName, null, null);
        }

    }


}
