package com.heibaibi.java.base.multiThread;

public class ThreadBaseTest extends Thread {

    public ThreadBaseTest() {
    }

//  添加设置线程名的构造方法。
    public ThreadBaseTest(String name) {
        super(name);
    }
    //多线程的运行体
    @Override
    public void run() {
        for(int i=0;i<20;i++){
//          Thread.currentThread().setName("村长");
            System.out.println(Thread.currentThread().getName());
            System.out.println(getName()+i);
        }
    }
}
