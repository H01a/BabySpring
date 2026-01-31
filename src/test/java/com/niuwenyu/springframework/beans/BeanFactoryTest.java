package com.niuwenyu.springframework.beans;

import com.niuwenyu.springframework.beans.context.ApplicationContext;
import com.niuwenyu.springframework.beans.context.support.ClassPathXmlApplicationContext;
import com.niuwenyu.springframework.beans.core.io.DefaultResourceLoader;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;
import com.niuwenyu.springframework.beans.factory.config.BeanReference;
import com.niuwenyu.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.niuwenyu.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
    public void test_BeanFactory() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo("a");
    }

    @Test
    public void testBeanDefinitionReader() throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory, new DefaultResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:beans.xml");
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");

        userService.queryUserInfo("a");
    }


    @Test
    public void testContext() throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException{
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:beans.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println(userService.getUid());

    }

}
