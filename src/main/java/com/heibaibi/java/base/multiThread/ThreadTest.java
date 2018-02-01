package com.heibaibi.java.base.multiThread;

import org.junit.Test;

import java.util.Arrays;
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

    /**
     * 锁是个标志。线程具有获取这个标志的权限，synchronized用来验证此线程是否具有锁。一个对象仅有一个锁。一个正常的对象由两部分组成，一部分是对象本身的属性（放在堆中），另外一部分是Class对象（放在方法区中）。若把这“锁”标志放在Class对象上，那线程之间能够同步。若把锁放在对象本身上，那只有此对象的线程之间才能同步工作。
     */
    @Test
    public void testSynchronized(){
        String lockObject="ABC";
//        String lockObject02="DEF";

        ThreadService threadService = new ThreadService(lockObject);
//        ThreadService threadService2= new ThreadService(lockObject02);
        RunnableLock runnableLock = new RunnableLock(threadService);
//       RunnableLock runnableLock2 = new RunnableLock(threadService2);
        Thread thread01 = new Thread(runnableLock);
        thread01.setName("无极");
//        Thread thread02 = new Thread(runnableLock2);
        Thread thread02 = new Thread(runnableLock);
        thread02.setName("霸王别姬");

        thread01.start();
        thread02.start();

        //      主线程休眠。
        try {
            Thread.sleep(7000);
        } catch (Exception e) {
            System.out.println("主线程休眠异常");
        }
    }

    /**
     * 这些方法都是在 同步中才有效。同时这些方法在使用时必须标明所属锁，这样才可以明确出这些方法操作的到底是哪个锁上的线程。
     */
    @Test
    public void  testThreadCommunication(){
        ThreadService threadService = new ThreadService("DBF");
        ThreadServiceInput threadServiceInput = new ThreadServiceInput(threadService);
        ThreadServiceOutput threadServiceOutput = new ThreadServiceOutput(threadService);
        Thread input = new Thread(threadServiceInput);
        Thread ouput = new Thread(threadServiceOutput);

        input.start();
        ouput.start();

        try {
            Thread.sleep(700);
        } catch (Exception e) {
            System.out.println("主线程休眠异常");
        }
    }

}
