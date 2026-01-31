package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;
import com.niuwenyu.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenyuniu
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private final HashMap<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }


    /**
     * BeanFactoryPostProcessor是一种特殊的bean，在普通bean实例化前就被实例化了
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> resultMap = new HashMap<>();
        for(Map.Entry<String, BeanDefinition> entry: beanDefinitionMap.entrySet()){
            if(type.isAssignableFrom(entry.getValue().getBeanClass())){
                resultMap.put(entry.getKey(), (T) getBean(entry.getKey()));
            }
        }

        return resultMap;
    }

    @Override
    public List<String> getBeanDefinitionNames() {
        return null;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeansException {
        if(beanDefinitionMap.containsKey(beanName)){
            throw new BeansException("BeanDefinition already exists");
        }else{
            beanDefinitionMap.put(beanName, beanDefinition);
        }
    }
}
