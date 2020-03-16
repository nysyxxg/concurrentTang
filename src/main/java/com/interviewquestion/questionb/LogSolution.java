package com.interviewquestion.questionb;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogSolution {
    public static void main(String[] args) throws InterruptedException {

        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
        ExecutorService executorService = Executors.newFixedThreadPool(4);  // 使线程池
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // 使用阻塞的take
                       // String log = queue.take();
                        System.out.println("队列中元素个数：" + queue.size());
                       if(queue.size()>0) {
                           String log = queue.poll(); // 使用非阻塞的方法
                           parseLog(log);
                       }else{
                           break;
                       }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        long begin = System.currentTimeMillis() / 1000;
        System.out.println("begin:" + begin);
            /*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
            修改程序代码，开四个线程让这16个对象在4秒钟打完。
            */
        for (int i = 0; i < 16; i++) {  //这行代码不能改动
            final String log = "" + (i + 1);//这行代码不能改动
            queue.put(log);
        }

        for (int i = 0; i < 4; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
        // 如何判断线程池中任务执行完成？？
        while (true) {
            if (executorService.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis() / 1000;
        System.out.println("end: " + end);
        System.out.println("花费的时间: " + (end - begin));
    }

    //parseLog方法内部的代码不能改动
    public static void parseLog(String log) {
        System.out.println(log + ":" + (System.currentTimeMillis() / 1000));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
