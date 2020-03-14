package com.interviewquestion.questionb;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class MyLogSolution {
    private  volatile static  boolean FLAG = true;
    public static void main(String[] args) throws Exception {
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
            /*
            模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
            修改程序代码，开四个线程让这16个对象在4秒钟打完。
            */
        BlockingQueue<String>   blockingQueue = new ArrayBlockingQueue<String>(16);
        for(int i=0;i<16;i++){  //这行代码不能改动
            final String log = "输出日志----"+(i+1);//这行代码不能改动
            {
                // LogQuestion.parseLog(log);
                blockingQueue.put(log);
            }
        }

        // 创建四个线程
        for(int i =1; i<=4; i++){
            new Thread(()->{
                try {
                    while(FLAG){
                        String log =  blockingQueue.take();
                        LogQuestion.parseLog("当前线程....."+ Thread.currentThread().getName()+  "\t " + log);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // 让主线程休眠4秒，然后设置标识
        Thread.sleep(4000);
        MyLogSolution.FLAG = false;
        System.out.println(" 主线程....."+ Thread.currentThread().getName() +"-----" + FLAG);
    }


    //parseLog方法内部的代码不能改动
    public static void parseLog(String log){
        System.out.println(log+":"+(System.currentTimeMillis()/1000));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
