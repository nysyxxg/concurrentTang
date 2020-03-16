package com.interviewquestion.questionc;


import lombok.Synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题C：
 * 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,
 * 如此反复, 无限循环下去 .
 */
public class QuestionC {
    static volatile boolean bl = true;

    static Object object = new Object();

    public static class MySubThread implements Runnable {
        int index = 0;

        MySubThread(int index) {
            this.index = index;
        }

        @Override
        public void run()   {
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

    public static void main(String[] args) {
        int index = 10;
        new Thread(new MySubThread(index)).start();

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//        }

        while (true) {
            synchronized (object) {
                if(bl){// 第一次进入，会等待，直到子线程来唤醒
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    for (int i = 0; i < 20; i++) {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "-----" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }

                bl = true;
                object.notify();
            }
        }
    }
}
