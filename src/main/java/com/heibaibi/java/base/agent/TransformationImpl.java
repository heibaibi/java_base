package com.heibaibi.java.base.agent;

public class TransformationImpl implements Transformation {

    @Override
    public void addTransformation(Object bean, String userId, String token, Long tt) {
        System.out.println("这是交易模块的添加功能");
    }

    @Override
    public void modifyTransformation(Object bean, String userId, String token, Long tt) {

    }

    @Override
    public void getTransformation(Integer id, String userId, String token, Long tt) {
        System.out.println("这是交易模块的获取单个交易功能");
    }

    @Override
    public void modifyTransformationStatus(Object id, String status, String userId, String token, Long tt) {

    }
}
