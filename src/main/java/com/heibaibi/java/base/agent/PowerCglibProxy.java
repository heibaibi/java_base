package com.heibaibi.java.base.agent;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PowerCglibProxy implements MethodInterceptor {

    private String userId;
    private String token;

    public PowerCglibProxy(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//       代理业务实现区。
        System.out.println("代理业务实现前区");
        Object resultObject = methodProxy.invokeSuper(object, objects);
        System.out.println("代理业务实现后区");

        return resultObject;
    }
}
