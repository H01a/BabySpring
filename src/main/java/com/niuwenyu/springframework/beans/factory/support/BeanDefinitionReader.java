package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.core.io.Resource;
import com.niuwenyu.springframework.beans.core.io.ResourceLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public interface BeanDefinitionReader {
    public BeanDefinitionRegistry getRegistry();
    public ResourceLoader getResourceLoader();
    public void loadBeanDefinitions(Resource resource) throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException;
    public void loadBeanDefinitions(Resource... resources) throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException;
    public void loadBeanDefinitions(String path) throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException;
}
