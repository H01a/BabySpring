package com.niuwenyu.springframework.beans.factory;

import com.niuwenyu.springframework.beans.BeansException;

/**
 * @author wenyuniu
 */
public interface BeanFactory {
    /**
     * 通过类名获取bean
     * @param name bean名
     * @return bean
     * @exception
     */
    public Object getBean(String name) throws BeansException;

    /**
     * 为了解决具有构造函数的bean的实例化问题，重构getBean方法，新增可以接收构造函数参数的getBean
     * @param name bean名
     * @param args 构造函数的参数，可变参数：
     *             1. 可变参数必须位于参数列表的最后一位
     *             2. 参数列表中只能有一个可变参数
     *             3. 在方法的实现中，可以通过arg[i]来获取第i个传入的Obj类型的参数
     * @return bean
     */
    public Object getBean(String name, Object... args) throws BeansException;
}
