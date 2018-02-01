package com.heibaibi.java.base.multiThread;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ThreadTest {

    @Test
    public void testThreadBaseTest(){
//      使用Thread类实现多线程。
        /*ThreadBaseTest threadBaseTest01 = new ThreadBaseTest();
        ThreadBaseTest threadBaseTest02 = new ThreadBaseTest();*/

        /*ThreadBaseTest threadBaseTest01 = new ThreadBaseTest("喜羊羊");
        ThreadBaseTest threadBaseTest02 = new ThreadBaseTest("灰太狼");
//      创建完线程之后，再设置姓名。
        threadBaseTest01.setName("懒羊羊");

        threadBaseTest01.start();
        threadBaseTest02.start();*/

//      使用Runable接口实现多线程任务。
        RunableBaseTest runableBaseTest = new RunableBaseTest();
        RunableBaseTest runableBaseTest1 = new RunableBaseTest();
        Thread thread01 = new Thread(runableBaseTest);
        thread01.setName("雪狼");
        Thread thread02 = new Thread(runableBaseTest1);
        thread02.setName("昆仑");

        thread01.start();
        thread02.start();

//        为了能看到自定义线程运行结果。
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("主线程休眠异常");
        }
    }

    @Test
    public void testThreadRunableTest(){
//      匿名内部线程。  我没想到他的优势在哪，或者是适用场地。
        new Thread(){
            public void run(){
                Thread.currentThread().setName("灰太狼");
                System.out.println(Thread.currentThread().getName());
            }
        }.start();


        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Thread.currentThread().setName("鲜花盔甲");
                        System.out.println(Thread.currentThread().getName());
                    }
                }
        ){}.start();

//      主线程休眠。
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("主线程休眠异常");
        }
    }

    @Test
    public void testExecutors(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
//       传入Runnable实现线程池。
        /*executorService.submit(new RunableBaseTest() );
        executorService.submit(new RunableBaseTest() );
        executorService.submit(new RunableBaseTest() );*/

//        传入Callable实现线程池
        /*executorService.submit(new CallableBaseTest(25));
        executorService.submit(new CallableBaseTest(26));
        executorService.submit(new CallableBaseTest(27));*/

//      测试Callable线程存在返回值。
        Future<Integer[]> submit27 = executorService.submit(new CallableBaseTest(27));
        Future<Integer[]> submit25 = executorService.submit(new CallableBaseTest(25));

        Integer[] value27=null;
        Integer[] value25=null;
        try {
            value27= submit27.get();
            value25=  submit25.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(value27)+"---"+Arrays.toString(value25));
        executorService.shutdown();
        //      主线程休眠。
        try {
            Thread.sleep(700);
        } catch (Exception e) {
            System.out.println("主线程休眠异常");
        }
    }
}
