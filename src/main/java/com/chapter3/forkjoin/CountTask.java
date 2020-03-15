package com.chapter3.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long>{

    private static final int THRESHOLD = 10000;

    private long start;

    private long end;

    public CountTask(long start, long end){
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end-start)<THRESHOLD;
        if (canCompute){
            for (long i=start;i<end;i++){
                sum+=i;
            }
        }else {
            //分成100个小人物
            long step = (start+end)/100;  // 分割成100个任务
            ArrayList<CountTask>subTasks = new ArrayList<CountTask>();
            long pos = start;
            for (int i=0;i<100;i++){// 分割成100个任务，创建100个subTask
                long lastone = pos+step;
                if (lastone>end){
                    lastone = end;
                }
                CountTask subTask = new CountTask(pos,lastone);
                pos+=step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (CountTask t:subTasks){
                sum+=t.join(); // 主线程等待 所有的任务都完成
            }
        }

        return sum;
    }


}
