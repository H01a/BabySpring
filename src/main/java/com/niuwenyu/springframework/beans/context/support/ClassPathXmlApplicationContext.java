package com.niuwenyu.springframework.beans.context.support;

import com.niuwenyu.springframework.beans.BeansException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    String[] configLocations;

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configLocation) throws IOException, ParserConfigurationException, ClassNotFoundException, BeansException, SAXException {
        this(new String[]{configLocation});
    }
    public ClassPathXmlApplicationContext(String[] configLocations) throws IOException, ParserConfigurationException, ClassNotFoundException, BeansException, SAXException {
        this.configLocations = configLocations;
        refresh();
    }
}
