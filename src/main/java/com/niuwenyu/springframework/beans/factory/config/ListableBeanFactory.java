package com.niuwenyu.springframework.beans.factory.config;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.BeanFactory;

import java.util.List;
import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
    public List<String> getBeanDefinitionNames();
}
