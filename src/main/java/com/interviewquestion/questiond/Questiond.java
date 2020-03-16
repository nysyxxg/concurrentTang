package com.interviewquestion.questiond;


/**
 * 面试题C：
 * 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,
 * 如此反复, 无限循环下去 .
 */
public class Questiond {

    public static void main(String[] args) {

        boolean bl = true;
        Object object = new Object();
        int index = 10;
        new Thread(new MySubThread(index, bl, object)).start();

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//        }

        while (true) {
            synchronized (object) {
                if (bl) {// 第一次进入，会等待，直到子线程来唤醒
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    for (int i = 0; i < 10; i++) {
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
