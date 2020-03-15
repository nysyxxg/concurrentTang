package com.chapter3.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时调度线程池
 */
public class ScheduledExecutorServiceDemo {


    public static  void main(String args[]){

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);

        /**
         * command：执行线程
         * initialDelay：初始化延时
         * period：两次开始执行最小间隔时间
         * unit：计时单位
         */
        ses.scheduleAtFixedRate(new MyThreadTask(),0,2, TimeUnit.SECONDS);  //两秒执行一次
    }


    public static  class MyThreadTask implements  Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(4000);
                System.out.println(System.currentTimeMillis() +  " 线程ID = " +  Thread.currentThread().getId()+"执行了" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
