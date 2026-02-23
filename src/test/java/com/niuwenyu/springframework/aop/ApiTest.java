package com.niuwenyu.springframework.aop;


import com.niuwenyu.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.niuwenyu.springframework.aop.framework.Cglib2AopProxy;
import com.niuwenyu.springframework.aop.framework.JdkDynamicAopProxy;
import com.niuwenyu.springframework.beans.IUserService;
import com.niuwenyu.springframework.beans.UserService;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test() {

        IUserService userService = new UserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(
                new AspectJExpressionPointcut(
                        "execution(* com.niuwenyu.springframework.beans.UserService.*(..))"
                )
        );

        // ==========================
        // JDK 动态代理（必须用接口接收）
        // ==========================

        IUserService proxy_jdk =
                (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();

        proxy_jdk.queryUserInfo("test");

        // ==========================
        // CGLIB 代理（可以强转实现类）
        // ==========================

        UserService proxy_cglib =
                (UserService) new Cglib2AopProxy(advisedSupport).getProxy();

        proxy_cglib.queryUserInfo("test");
    }
}
