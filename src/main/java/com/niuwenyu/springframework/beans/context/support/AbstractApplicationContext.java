package com.niuwenyu.springframework.beans.context.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.context.ConfigurableApplicationContext;
import com.niuwenyu.springframework.beans.core.io.DefaultResourceLoader;
import com.niuwenyu.springframework.beans.core.io.Resource;
import com.niuwenyu.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.niuwenyu.springframework.beans.factory.config.BeanPostProcessor;
import com.niuwenyu.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.niuwenyu.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * # protected 子类 + 同package可见
 * ~ default  同package可见
 * @author wenyuniu
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext{
    @Override
    public void refresh() throws BeansException, IOException, ParserConfigurationException, ClassNotFoundException, SAXException {
        // 1.创建BeanFactory并加载BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 2.实例化前执行BeanFactoryPostProcessor，修改beanDefinition等操作
        // 先找到当前容器中所有类型为BeanFactoryPostProcessor的bean，依次调用其方法
        invokeBeanFactoryPostProcessors(beanFactory);

        // 3.实例化前注册BeanPostProcessor
        registerBeanPostProcessors(beanFactory);

        // 4.提前实例化单例bean


    }


    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, ? extends BeanPostProcessor> beanPostProcessorMap =
                beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor: beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 由AbstractRefreshableApplicationContext实现
     */
    protected abstract void refreshBeanFactory() throws IOException, ParserConfigurationException, ClassNotFoundException, SAXException, BeansException;

    /**
     * 由AbstractRefreshableApplicationContext实现
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor: beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public List<String> getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }
}
