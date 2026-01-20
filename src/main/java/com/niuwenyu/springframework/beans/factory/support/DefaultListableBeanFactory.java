package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;

/**
 * @author wenyuniu
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    private final HashMap<Object, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeansException {
        if(beanDefinitionMap.containsKey(beanName)){
            throw new BeansException("BeanDefinition already exists");
        }else{
            beanDefinitionMap.put(beanName, beanDefinition);
        }
    }
}
