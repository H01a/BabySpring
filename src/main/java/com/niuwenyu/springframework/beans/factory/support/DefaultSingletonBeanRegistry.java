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

    Object addSingleton(String beanName, Object bean) throws BeansException {
        if(bean == null){
            throw new BeansException("bean is null");
        }else{
            singletonObjects.put(beanName, bean);
        }
        return bean;
    }
}