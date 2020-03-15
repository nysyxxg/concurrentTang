package com.chapter2.consumeone;

public class Application {

	public static void main(String[] args) {
		Service service = new Service();
		Runnable produce1 = new MyThreadProduce(service);
		Runnable consume1 = new MyThreadConsume(service);
		new Thread(produce1, "生产者启动1").start();
		new Thread(consume1, "消费者启动1").start();

		Runnable produce2 = new MyThreadProduce(service);
		Runnable consume2 = new MyThreadConsume(service);
		new Thread(produce2, "生产者启动2").start();
		new Thread(consume2, "消费者启动2").start();
	}

	public static void main1(String[] args) {
		Service service = new Service();
		Runnable produce = new MyThreadProduce(service);
		Runnable consume = new MyThreadConsume(service);
		new Thread(produce, "生产者启动").start();
		new Thread(consume, "消费者启动").start();
	}

}
