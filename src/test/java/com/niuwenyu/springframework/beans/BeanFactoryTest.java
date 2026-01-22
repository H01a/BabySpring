package com.niuwenyu.springframework.beans;

import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;
import com.niuwenyu.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BeanFactoryTest {

    @Test
    public void testBeanFactory(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        List<Integer> l = new ArrayList<>();
        l.add(1);

        try {
            defaultListableBeanFactory.registerBeanDefinition("myList", new BeanDefinition(l.getClass()));
            defaultListableBeanFactory.getBean("myList");
            defaultListableBeanFactory.getBean("myList");
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getBeanWithConstructorParams(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        try {
            defaultListableBeanFactory.registerBeanDefinition("userServiceWithoutConstructorParams", new BeanDefinition(UserService.class));
            defaultListableBeanFactory.registerBeanDefinition("userServiceWithConstructorParams", new BeanDefinition(UserService.class));
            UserService userService1 = (UserService) defaultListableBeanFactory.getBean("userServiceWithoutConstructorParams");
            UserService userService2 = (UserService) defaultListableBeanFactory.getBean("userServiceWithConstructorParams", "kk");
            System.out.println(userService1.getMsg());
            System.out.println(userService2.getMsg());
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

    }

}
