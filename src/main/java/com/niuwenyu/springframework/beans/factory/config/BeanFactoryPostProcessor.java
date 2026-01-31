package com.niuwenyu.springframework.beans.factory.config;

/**
 * @author wenyuniu
 */
public interface BeanFactoryPostProcessor {
    /**
     * 注册BeanDefinition后对bean定义进行自定义的修改操作
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
