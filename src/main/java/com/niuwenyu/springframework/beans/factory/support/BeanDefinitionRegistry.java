package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;

/**
 * @author wenyuniu
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册BeanDefinition
     * @param beanName beanName
     * @param beanDefinition beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
