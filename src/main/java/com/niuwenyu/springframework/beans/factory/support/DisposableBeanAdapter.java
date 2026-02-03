package com.niuwenyu.springframework.beans.factory.support;

import java.lang.reflect.InvocationTargetException;

public class DisposableBeanAdapter implements DisposableBean{

    private final Object bean;
    private final String beanName;
    private String destroyMethod;

    public DisposableBeanAdapter(Object bean, String beanName, String destroyMethod) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethod = destroyMethod;
    }


    @Override
    public void destory() {
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destory();
        }else{
            if(destroyMethod.length() > 0){
                try {
                    bean.getClass().getSuperclass().getMethod(destroyMethod).invoke(bean);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
