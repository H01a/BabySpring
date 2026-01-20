package com.niuwenyu.springframework.beans.factory.config;

/**
 * @author wenyuniu
 */
public interface SingletonBeanRegistry {
    /**
     * 返回单例对象
     * @param beanName bean名
     * @return 单例对象
     */
    public Object getSingleton(String beanName);
}
