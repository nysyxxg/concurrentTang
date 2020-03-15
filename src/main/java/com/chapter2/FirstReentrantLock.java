package com.chapter2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 的简单实用
 * @author tangj
 */
public class FirstReentrantLock {

    public static void main(String[] args) {
        Runnable runnable = new ReentrantLockThread();
        new Thread(runnable, "a").start();
        new Thread(runnable, "b").start();
    }

}

class ReentrantLockThread implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 线程，获得锁，开始执行。");
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
            System.out.println(Thread.currentThread().getName() + " 线程，获得锁，开始完成。");
        } finally {
            lock.unlock();
        }
    }
}