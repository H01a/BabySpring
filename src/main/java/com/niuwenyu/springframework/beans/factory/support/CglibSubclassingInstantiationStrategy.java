package com.niuwenyu.springframework.beans.factory.support;

import com.niuwenyu.springframework.beans.BeansException;
import com.niuwenyu.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * 在 Java 9 之后，JDK 引入了模块系统（Project Jigsaw），
 * 默认情况下禁止外部库通过反射访问 java.base
 * 模块中的私有/保护方法（例如 ClassLoader.defineClass）。
 * 由于 CGLIB 需要通过反射调用 defineClass 来在内存中生成 Bean 的子类，
 * 而 JDK 17 把这扇门给关上了，所以抛出了 InaccessibleObjectException。
 * 需要添加启动参数--add-opens java.base/java.lang=ALL-UNNAMED
 * cglib实例化的实现
 * @author wenyuniu
 */

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException {
        Class<?> clazz = beanDefinition.getBeanClass();

        Enhancer enhancer = new Enhancer();
        // 通过创建子类的方式实例化
        enhancer.setSuperclass(clazz);
        // 设置回调函数
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(constructor != null){
            return enhancer.create(constructor.getParameterTypes(), args);
        }else{
            return enhancer.create();
        }
    }
}

/*
  为什么Spring要有两种实例化的方式？
  1.JDK实例化：优先使用
  2.cglib实例化：处理动态子类代理的情况，或者当系统发现需要对 Bean 进行字节码增强时使用。
 */

//    JDK 动态代理：要求目标类必须实现接口。它像是一个“经纪人”，拿着接口合同去办事。
//        CGLIB 动态子类代理：不要求接口。它直接继承目标类，生成一个子类。
//        情况描述： 当你有一个类 UserService（没有实现任何接口），但你想在调用 save() 方法前后加日志。
//        JDK 代理做不到，此时 CGLIB 就会出场，动态创建一个 UserService$$EnhancerByCGLIB 类，这个类是 UserService 的儿子。
