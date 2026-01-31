package com.niuwenyu.springframework.beans.factory.config;

/**
 * @author wenyuniu
 */
public interface BeanPostProcessor {
    /**
     * 实例化后初始化前，对Bean进行修改
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * 实例化后初始化后，对Bean进行修改
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
