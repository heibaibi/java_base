package com.heibaibi.java.base.multiThread;

public class RunableBaseTest implements Runnable {

    @Override
    public void run() {
        for(int i=0;i<20;i++){
//            不太容易看出来这是不是多线程，因此添加这个。
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"---"+i);
        }
    }
}
