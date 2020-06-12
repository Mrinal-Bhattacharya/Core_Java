package com.junit.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSeq implements Runnable {
	private AtomicInteger sharedCounter;
	private final int threadPosition;
	private Object lock;
	private int totalThread;

	public ThreadSeq(int totalThread, AtomicInteger sharedCounter, int threadid, Object lock) {
		this.sharedCounter = sharedCounter;
		this.threadPosition = threadid;
		this.lock = lock;
		this.totalThread = totalThread;
	}

	@Override
	public void run() {
		synchronized (lock) {
			while (sharedCounter.get() < 10) {
				if (sharedCounter.get() % totalThread != threadPosition) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					//System.out.println(Thread.currentThread().getName() + " : " + sharedCounter.get() % totalThread);
					System.out.println(Thread.currentThread().getName() + " : " + sharedCounter.getAndIncrement());
					
					lock.notifyAll();
				}
			}
		}
	}

}
