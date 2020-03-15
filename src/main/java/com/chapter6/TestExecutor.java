package com.chapter6;

import java.util.concurrent.*;

/**
 * Callable 的使用
 */
public class TestExecutor {
    public static void main(String args[]){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        test2(executorService);

        executorService.shutdown();
    }

    public static void test1(ExecutorService executorService){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });
    }

    public static void test2(ExecutorService executorService){
        Future future = executorService.submit(new MyTestTask() );
        try {
            System.out.println("future结果 "+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static class MyTestTask implements  Callable{
        @Override
        public Object call() throws Exception {
            return "测试结果";
        }
    }
}
