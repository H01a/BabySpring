package com.niuwenyu.springframework.beans.factory.config;

import com.niuwenyu.springframework.beans.PropertyValues;

/**
 * @author wenyuniu
 */
public class BeanDefinition {
    private Class<?> beanClass;
    private PropertyValues propertyValues;

    private String initMethod;
    private String destoryMethod;

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getDestoryMethod() {
        return destoryMethod;
    }

    public void setDestoryMethod(String destoryMethod) {
        this.destoryMethod = destoryMethod;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
