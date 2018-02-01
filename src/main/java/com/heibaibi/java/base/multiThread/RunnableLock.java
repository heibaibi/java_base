package com.heibaibi.java.base.multiThread;

public class RunnableLock implements Runnable{

    private ThreadService threadService;

    public RunnableLock() {
    }

    public RunnableLock(ThreadService threadService) {
        this.threadService = threadService;
    }

    @Override
    public void run() {
        threadService.timeService();
    }
}
