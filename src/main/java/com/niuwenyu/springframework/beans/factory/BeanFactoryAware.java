package com.niuwenyu.springframework.beans.factory;

public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory);

}
