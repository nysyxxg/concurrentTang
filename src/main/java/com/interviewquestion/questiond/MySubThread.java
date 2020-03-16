package com.interviewquestion.questiond;

public class MySubThread implements Runnable {
    private  boolean bl = true;
    private Object object = new Object();
    private int index = 0;

    MySubThread(int index,boolean bl,Object object) {
        this.index = index;
        this.bl = bl;
        this.object = object;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (object) {
                if (!bl) {  // 因为 bl 开始是 true，第一次进入，不会等待
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
                try {
                    for (int i = 0; i < index; i++) {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "-----" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
                bl = false; // 设置为false
                object.notify(); // 唤醒主线程
            }
        }
    }
}