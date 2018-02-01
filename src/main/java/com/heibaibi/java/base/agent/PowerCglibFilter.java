package com.heibaibi.java.base.agent;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class PowerCglibFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
//        过滤规则。
        String methodName = method.getName();
        if(methodName!=null&&methodName.contains("get")){
            return 0;
        }
        return 1;
    }
}
