package com.heibaibi.java.base.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {
    //    被代理对象。
    private Object object;

    //  使用代理对象的时候吧被代理者传过来。  实际只要传过来就行了，是不是这用方法都无所谓。
    public InvocationHandlerImpl(Object object) {
        this.object = object;
    }

    //    继承此接口要实现的类。
//    此方法就是代理JDK代理逻辑实现区。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前处理");

//      执行被代理类的被代理方法。
        Object byAgent = method.invoke(object, args);

        System.out.println("代理后处理");
        return byAgent;
    }
}
