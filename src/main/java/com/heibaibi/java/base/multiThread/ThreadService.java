package com.heibaibi.java.base.multiThread;

/**
 * 锁是个标志。线程具有获取这个标志的权限，synchronized用来验证此线程是否具有锁。一个对象仅有一个锁。一个正常的对象由两部分组成，一部分是对象本身的属性（放在堆中），另外一部分是Class对象（放在方法区中）。若把这“锁”标志放在Class对象上，那线程之间能够同步。若把锁放在对象本身上，那只有此对象的线程之间才能同步工作。
 */
public class ThreadService {
    private String lockObject;

    public ThreadService(String lockObject) {
        this.lockObject = lockObject;
    }

    public synchronized void timeService() {
        try {
//            synchronized (lockObject) {
                System.out.println("线程名称为：" + Thread.currentThread().getName()
                        + "在" + System.currentTimeMillis() + "进入同步块");
                Thread.sleep(3000);
                System.out.println("线程名称为：" + Thread.currentThread().getName()
                        + "在" + System.currentTimeMillis() + "离开同步块");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
