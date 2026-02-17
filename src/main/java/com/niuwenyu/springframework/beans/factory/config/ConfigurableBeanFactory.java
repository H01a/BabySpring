package com.niuwenyu.springframework.beans.factory.config;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory{
    // 接口定义变量需要初始化，并且不能为private
    String SCOPE_SINGLETON = "SCOPE_SINGLETON";
    String SCOPE_PROTOTYPE = "SCOPE_PROTOTYPE";
    void destorySingletons();

}
