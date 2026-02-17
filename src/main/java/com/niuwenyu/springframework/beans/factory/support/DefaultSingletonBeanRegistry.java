package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;
import com.niuwenyu.springframework.beans.factory.config.SingletonBeanRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenyuniu
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    Object NULL_OBJECT;
    private final HashMap<String, Object> singletonObjects = new HashMap<>();
    private final HashMap<String, DisposableBean> disposableBeans = new HashMap<>();

    public List<DisposableBean> getDisposableBeans(){
        List<DisposableBean> beans = new ArrayList<>();
        for(Map.Entry<String, DisposableBean> entry: disposableBeans.entrySet()){
            beans.add(entry.getValue());
        }
        return beans;
    }


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    Object addSingleton(String beanName, Object bean) throws BeansException {
        if(bean == null){
            throw new BeansException("bean is null");
        }else{
            singletonObjects.put(beanName, bean);
        }
        return bean;
    }

    void addDisposableBean(String beanName, DisposableBean bean){
        disposableBeans.put(beanName, bean);
    }
}