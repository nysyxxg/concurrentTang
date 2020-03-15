package com.chapter3.threadpool;

import java.util.concurrent.*;

public class ThreadFactoryDemo {

    public static class MyTask1 implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+" Thrad ID:"+Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){

        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                System.out.println("创建线程"+t);
                return  t;
            }
        });

        MyTask1 task = new MyTask1();
        for (int i = 0;i<=4;i++){
           es.submit(task);
        }
    }
}
