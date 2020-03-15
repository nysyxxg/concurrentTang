package com.chapter3.threadutil;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 它的作用就是会让所有线程都等待完成后才会继续下一步行动。
 *
 * 举个例子，就像生活中我们会约朋友们到某个餐厅一起吃饭，有些朋友可能会早到，有些朋友可能会晚到，但是这个餐厅规定必须等到所有人到齐之后才会让我们进去。这里的朋友们就是各个线程，餐厅就是 CyclicBarrier。
 *
 *
 */
public class CyclicBarrierTest {

    public static void  main(String args[]){
        final int N = 10;
        boolean flag =false;
        CyclicBarrier cyclic = new CyclicBarrier(N,new CyclicBarrierDemo.BarrierRun(flag,N));
        System.out.println("集合队伍");

        Thread[] allSoldier = new Thread[N];
        for (int i=0;i<N;i++){  // 创建10个线程，模拟10个士兵，去【报到】
            System.out.println("士兵"+i+"报道");
            allSoldier[i] = new Thread(new CyclicBarrierDemo.Soldier(cyclic,"士兵"+i));
            allSoldier[i].start();
        }
    }
}
