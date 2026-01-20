package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;
import com.niuwenyu.springframework.beans.factory.config.SingletonBeanRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author wenyuniu
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final HashMap<String, Object> singletonObjects = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    Object addSingleton(String beanName, BeanDefinition beanDefinition) throws BeansException {
        if (singletonObjects.containsKey(beanName)) {
            throw new BeansException("Bean already exists");
        } else {
            try {
                Object bean = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
                singletonObjects.put(beanName, bean);
                return bean;

            } catch (InvocationTargetException | InstantiationException | NoSuchMethodException |
                     IllegalAccessException e) {
                throw new BeansException("Something wrong");
            }
        }
    }
}