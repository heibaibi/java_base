package com.heibaibi.java.base.agent;

/**
 * 静态代理方法。
 */
public class TransformationImplProxy implements Transformation {

//    添加私有代理对象 接口 。
    private Transformation transformation;

    public  TransformationImplProxy(Transformation transformation) {
        this.transformation = transformation;
    }

    @Override
    public void addTransformation(Object bean, String userId, String token, Long tt) {
        System.out.println("此是静态代理前执行的部分");
        transformation.addTransformation(bean,userId, token, tt);
        System.out.println("此是代理后执行的部分");
    }

    @Override
    public void modifyTransformation(Object bean, String userId, String token, Long tt) {

    }

    @Override
    public void getTransformation(Integer id, String userId, String token, Long tt) {
        transformation.getTransformation(id,userId, token, tt);
    }

    @Override
    public void modifyTransformationStatus(Object id, String status, String userId, String token, Long tt) {

    }
}
