package com.niuwenyu.springframework.aop;


/**
 * @author wenyuniu
 * 切点表达式不就是提供了类与方法的信息嘛
 */
public interface Pointcut {
    ClassFilter getClassfilter();
    MethodMatcher getMethodMatcher();
}
