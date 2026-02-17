package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.BeanFactory;
import com.niuwenyu.springframework.beans.factory.FactoryBean;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;
import com.niuwenyu.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenyuniu
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements BeanFactory{
    List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return getBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        Object bean = getSingleton(name);
        if(bean == null){
            BeanDefinition beanDefinition = getBeanDefinition(name);
            bean = createBean(name, beanDefinition, args);
        }

        if(bean instanceof FactoryBean<?>){
            bean = getObjectFromFactoryBean((FactoryBean) bean, name);
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
    abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
