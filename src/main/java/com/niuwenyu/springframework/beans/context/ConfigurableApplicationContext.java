package com.niuwenyu.springframework.beans.context;

import com.niuwenyu.springframework.beans.BeansException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author wenyuniu
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 模版方法，编排bean的生命周期
     */
    void refresh() throws BeansException, IOException, ParserConfigurationException, ClassNotFoundException, SAXException;

    /**
     * 给JVM注册一个关闭前的回调钩子
     */
    void registerShutdownHook();

    void close();
}
