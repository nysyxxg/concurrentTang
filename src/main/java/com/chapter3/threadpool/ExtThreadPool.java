package com.chapter3.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtThreadPool {

    public static class MyTask2 implements Runnable{
        public String name;
        public MyTask2(String name){
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+" Thrad ID:"+Thread.currentThread().getId());
            try {
                 Thread.sleep(100);
                System.out.println(System.currentTimeMillis()+" Thrad ID:"+Thread.currentThread().getId() + "  正在执行的任务"+name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5,5,0L, TimeUnit.MICROSECONDS,new LinkedBlockingQueue<Runnable>()){
          @Override
          protected void beforeExecute(Thread t, Runnable r) {
              System.out.println("准备执行: "+((MyTask2)r).name );  // 线程执行前，打印输出
          }

          @Override
          protected void afterExecute(Runnable r, Throwable t) {
              System.out.println("执行完成: "+((MyTask2)r).name ); // 线程执行后
          }

        };
        for (int i = 0;i<5;i++){// 创建5个线程，提交到线程池，开始执行
            MyTask2 task2 = new MyTask2("Task "+i);
            es.execute(task2);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
