package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化有参构造函数的bean的顶层接口
 * 两种实现方式：1.JDK实例化， 2.cglib实例化
 * @author wenyuniu
 */
public interface InstantiationStrategy {
    /**
     * 实例化具有构造函数的bean
     * @param beanDefinition bean definition
     * @param beanName bean name
     * @param constructor java.lang.reflect下的类，为了拿到符合args格式的构造函数
     * @param args 传入的构造器参数
     * @return bean
     */
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException;
}
