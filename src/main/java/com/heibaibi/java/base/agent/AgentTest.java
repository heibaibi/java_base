package com.heibaibi.java.base.agent;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.Date;

public class AgentTest {

    /**
     * 静态代理
     * 代码重复量太大，而且扩展性不好。
     */
    @Test
    public void mothodOfStaticAgent(){
//      这是实现静态代理部分。
       /*
        这是结果。
        此是静态代理前执行的部分
        这是交易模块的添加功能
        此是代理后执行的部分
        */
        TransformationImplProxy transformationImplProxy = new TransformationImplProxy(new TransformationImpl());
        transformationImplProxy.addTransformation(new Object(),new String(),new String(),new Date().getTime());

        transformationImplProxy.getTransformation(new Integer(77),new String(),new String(),new Date().getTime());
    }

    /**
     * 测试JDK动态代理
     */
    @Test
    public void methodOfJDKAgent(){
//        被代理的对象，
        TransformationImpl transformationImpl = new TransformationImpl();

//      生成loader, interfaces, handler
        Class baseClass = transformationImpl.getClass();
        ClassLoader classLoader = baseClass.getClassLoader();
        Class[] interfaces = baseClass.getInterfaces();
        InvocationHandlerImpl handler = new InvocationHandlerImpl(transformationImpl);

//      创建动态代理对象
        Transformation transformation = (Transformation) Proxy.newProxyInstance(classLoader, interfaces, handler);

//      据http://blog.csdn.net/jiankunking/article/details/52143504#t0说：transformation继承自Proxy并实现了被代理的transformation接口，在实现Subject接口方法的内部，通过反射调用了
//      InvocationHandlerImpl的invoke方法。  暂时未到这个级别，先借用别人观点，待我看源码。

        transformation.addTransformation(new Object(),new String(),new String(),new Date().getTime());
    }

    /**
     * CGLIB例子
     *
     */
    @Test
    public void methodOfCGLIBAgent(){
//      UniversitiesService baseUniversitiesService = UniversitiesFactory.getBaseUniversitiesService();
        String userId = new String();
        String token = new String();
        UniversitiesService universitiesServiceByProxy = UniversitiesFactory.getUniversitiesServiceByProxy(new PowerCglibProxy(userId,token));
        universitiesServiceByProxy.addUniversities(new Object(),userId,token,new Date().getTime());
    }

    @Test
    public void methodOfCGLIBContainFilterAgent(){
//      UniversitiesService baseUniversitiesService = UniversitiesFactory.getBaseUniversitiesService();
        String userId = new String();
        String token = new String();
        UniversitiesService universitiesServiceByProxy = UniversitiesFactory.getUniversitiesServiceByProxyAndFilter(new PowerCglibProxy(userId,token));
        universitiesServiceByProxy.addUniversities(new Object(),userId,token,new Date().getTime());
        universitiesServiceByProxy.getUniversities(new Integer(77),userId,token,new Date().getTime());
    }
}
