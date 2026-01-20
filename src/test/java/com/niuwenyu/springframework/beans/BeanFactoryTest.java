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
}
