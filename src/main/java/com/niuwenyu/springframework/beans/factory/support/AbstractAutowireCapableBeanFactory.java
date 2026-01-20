package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;

/**
 * @author wenyuniu
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return addSingleton(beanName, beanDefinition);
    }
}
