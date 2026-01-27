package com.niuwenyu.springframework.beans.factory.xml;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.PropertyValue;
import com.niuwenyu.springframework.beans.PropertyValues;
import com.niuwenyu.springframework.beans.core.io.Resource;
import com.niuwenyu.springframework.beans.core.io.ResourceLoader;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;
import com.niuwenyu.springframework.beans.factory.config.BeanReference;
import com.niuwenyu.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.niuwenyu.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException {
        InputStream inputStream = resource.getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException {
        for(Resource resource: resources){
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String path) throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException {
        Resource resource = getResourceLoader().getResource(path);
        loadBeanDefinitions(resource);
    }


    /*
            Node (接口)
         ├── Element           ← 对应 XML 元素节点 <bean>, <property> ...
         ├── Attr              ← 属性节点
         ├── Text              ← 文本节点
         ├── Comment           ← 注释节点
         └── ...
     */
    public void doLoadBeanDefinitions(InputStream inputStream) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException, BeansException {
        BeanDefinitionRegistry registry = getRegistry();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();;
        Document document = documentBuilder.parse(inputStream);

        NodeList beanList = document.getElementsByTagName("bean");
        for(int i = 0; i < beanList.getLength(); i++){
            PropertyValues propertyValues = new PropertyValues();
            // 每一个bean标签
            Element bean = (Element) beanList.item(i);
            String beanName = bean.getAttribute("id");
            Class<?> beanClass = Class.forName(bean.getAttribute("class"));

            NodeList properties = bean.getElementsByTagName("property");
            for(int j = 0; j < properties.getLength();j ++){
                Element property = (Element) properties.item(j);
                String propertyName = property.getAttribute("name");
                // true: ref, false: value
                boolean propertyType = property.hasAttribute("ref");
                if(propertyType){
                    String propertyRef = property.getAttribute("ref");
                    propertyValues.addPropertyValue(new PropertyValue(propertyName, new BeanReference(propertyRef)));
                }else{
                    String propertyValue = property.getAttribute("value");
                    propertyValues.addPropertyValue(new PropertyValue(propertyName, propertyValue));
                }
            }
            BeanDefinition beanDefinition = new BeanDefinition(beanClass, propertyValues);
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
