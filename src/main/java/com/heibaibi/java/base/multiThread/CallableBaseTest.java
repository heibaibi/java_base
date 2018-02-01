package com.heibaibi.java.base.multiThread;

import java.util.concurrent.Callable;

public class CallableBaseTest implements Callable<Integer[]> {
    private Integer number;

    public CallableBaseTest() {
    }

    public CallableBaseTest(Integer number){
        this.number=number;
    }

    @Override
    public Integer[] call() throws Exception {
        Integer[] result = new Integer[9];
        for (int i=1;i<10;i++){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result[i-1] = number % i;
            System.out.println(Thread.currentThread().getName()+"----"+result[i-1]);
        }
        return result;
    }
}
