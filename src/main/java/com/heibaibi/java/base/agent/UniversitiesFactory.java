package com.heibaibi.java.base.agent;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class UniversitiesFactory {

    private UniversitiesFactory() { }

    public static UniversitiesService getBaseUniversitiesService(){
        UniversitiesService baseUniversitiesService = new UniversitiesService();
        return  baseUniversitiesService;
    }

    public static UniversitiesService getUniversitiesServiceByProxy(PowerCglibProxy powerCglibProxy){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UniversitiesService.class);
        enhancer.setCallback(powerCglibProxy);
//        UniversitiesService baseUniversitiesService = new UniversitiesService();
        return  (UniversitiesService)enhancer.create();
    }

    public static UniversitiesService getUniversitiesServiceByProxyAndFilter(PowerCglibProxy powerCglibProxy){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UniversitiesService.class);
//      setCallbacks中定义了所使用的拦截器，其中NoOp.INSTANCE是CGlib所提供的实际是一个没有任何操作的拦截器.
        enhancer.setCallbacks(new Callback[]{powerCglibProxy, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new PowerCglibFilter());
        return  (UniversitiesService)enhancer.create();
    }
}
