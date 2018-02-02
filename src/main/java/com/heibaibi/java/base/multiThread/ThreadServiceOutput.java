package com.heibaibi.java.base.multiThread;

public class ThreadServiceOutput implements Runnable {
    private ThreadService threadService;

    public ThreadServiceOutput(ThreadService threadService) {
        this.threadService = threadService;
    }

    @Override
    public void run() {
        synchronized (threadService) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(threadService.flag){
                try {
                    threadService.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName()+"----"+System.currentTimeMillis());

            System.out.println(threadService.toString());
            threadService.flag=true;
            threadService.notifyAll();
        }
    }
}
