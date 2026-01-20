package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.BeanFactory;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;

/**
 * @author wenyuniu
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory{
    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean == null) {
            BeanDefinition beanDefinition = getBeanDefinition(name);
            bean = createBean(name, beanDefinition);
        }
        return bean;
    }

    /**
     * 通过Bean名获取BeaDefinition
     * @param beanName Bean名
     * @return BeanDefinition
     */
    abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * 根据BeanDefinition创建名为beanName的Bean并返回
     * @param beanName Bean名
     * @param beanDefinition 包含class等信息
     * @return 创建好的Bean
     */
    abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
