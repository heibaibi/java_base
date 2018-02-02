package com.heibaibi.java.base.multiThread;

public class ThreadServiceInput implements Runnable {
    private ThreadService threadService;

    public ThreadServiceInput(ThreadService threadService) {
        this.threadService = threadService;
    }

    @Override
    public void run() {
        int i=0;
        while(true) {
//            根据测试结果实际线程之间不进行通信，只要线程用的同一个锁，也不会出现数据错乱问题。
//            只会出现返回值为null，因为有可能是输出先执行。
            synchronized (threadService) {
//               此使已经拿到锁，实际其他线程都已经在等待了。
                if(!threadService.flag){
                    try {
                        threadService.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName()+"----"+System.currentTimeMillis());
                    threadService.setName("无极");
                    threadService.setAddress("陈凯歌");
                } else {
                    System.out.println(Thread.currentThread().getName()+"----"+System.currentTimeMillis());
                    threadService.setName("霸王别姬");
                    threadService.setAddress("忘了叫什么名字");
                }
                i++;
                threadService.flag=false;
                threadService.notifyAll();
            }
        }
    }
}
