package com.niuwenyu.springframework.beans.factory;

import com.niuwenyu.springframework.beans.BeansException;

/**
 * @author wenyuniu
 */
public interface BeanFactory {
    /**
     * 通过类名获取bean
     * @param name bean名
     * @return bean
     */
    public Object getBean(String name) throws BeansException;
}
