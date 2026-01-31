package com.niuwenyu.springframework.beans.beanPostProcessor;

import com.niuwenyu.springframework.beans.UserService;
import com.niuwenyu.springframework.beans.factory.config.BeanPostProcessor;

public class UserServiceBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if(bean instanceof UserService){
            System.out.println("修改前的uid: " + ((UserService) bean).getUid());
            ((UserService) bean).setUid("10010");
            System.out.println("将uid修改为: " + "10010");
        }
        return bean;
    }
}
