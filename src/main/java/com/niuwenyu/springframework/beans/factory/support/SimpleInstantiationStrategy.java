package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * JDK实例化方式：
 * 1. 拿到该类某种参数结构的构造器并调用newInstance
 * 2. “某种参数结构”怎么知道？   调用constructor的getParameterTypes会返回Class数组，里面的元素是构造参数的类型
 * @author wenyuniu
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException {
        Class<?> clazz = beanDefinition.getBeanClass();
        Object bean;
        try {
            if (constructor != null) {
                bean = clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                bean = clazz.getDeclaredConstructor().newInstance();
            }
            return bean;
        }catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            throw new BeansException("实例化带有构造参数的bean失败");
        }
    }
}
