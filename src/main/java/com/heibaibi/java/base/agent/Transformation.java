package com.heibaibi.java.base.agent;


public interface Transformation {
    public void addTransformation(Object bean,String userId,String token,Long tt);

    public void modifyTransformation(Object bean,String userId,String token,Long tt);

    public void getTransformation(Integer id,String userId,String token,Long tt);

    public void  modifyTransformationStatus(Object id,String status,String userId,String token,Long tt);
}
