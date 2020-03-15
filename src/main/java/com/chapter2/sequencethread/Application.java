package com.chapter2.sequencethread;

/**
 *
 * 需求分析： A B C 三个线程，分别交替执行
 * @author xxg
 *
 */
public class Application {

	private static Runnable getThreadA(final Service service) {
		return new Runnable() {
			@Override
			public void run() {
				for (;;) {
					service.excuteA();
				}
			}
		};
	}

	private static Runnable getThreadB(final Service service) {
		return new Runnable() {
			@Override
			public void run() {
				for (;;) {
					service.excuteB();
				}
			}
		};
	}

	private static Runnable getThreadC(final Service service) {
		return new Runnable() {
			@Override
			public void run() {
				for (;;) {
					service.excuteC();
				}
			}
		};
	}

	public static void main(String[] args) {
		Service service = new Service();
		Runnable A = getThreadA(service);
		Runnable B = getThreadB(service);
		Runnable C = getThreadC(service);

		new Thread(B, "B").start();
		new Thread(A, "A").start();
		new Thread(C, "C").start();
	}

}
