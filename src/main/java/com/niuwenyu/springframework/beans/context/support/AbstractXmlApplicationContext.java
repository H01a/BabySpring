package com.niuwenyu.springframework.beans.context.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.core.io.Resource;
import com.niuwenyu.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.niuwenyu.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        for(String location: getConfigLocations()) {
            if(null != location){
                beanDefinitionReader.loadBeanDefinitions(location);
            }
        }
    }


    protected abstract String[] getConfigLocations();
}
