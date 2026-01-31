package com.niuwenyu.springframework.beans.context.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.BeanFactory;
import com.niuwenyu.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.niuwenyu.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.niuwenyu.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;
    @Override
    protected void refreshBeanFactory() throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException {
        beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
    }

    protected DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }
    @Override
    protected ConfigurableListableBeanFactory getBeanFactory(){
        return beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException;


}
