package com.heibaibi.java.base.agent;


public class UniversitiesService {

    public void  addUniversities(Object bean,String userId,String Token,Long tt){
        System.out.println("这是代理模块的添加高校");
    }

    public void  modifyUniversities(Object bean,String userId,String Token,Long tt){
        System.out.println("这是代理模块的修改高校");
    }

    public void  getUniversities(Integer id,String userId,String Token,Long tt){
        System.out.println("这是代理模块的获取单个高校");
    }

    public void modifyUniversitiesStatus(Integer id,String status,String userId,String Token,Long tt){
        System.out.println("这是代理模块的修改单个高校状态");
    }
}
