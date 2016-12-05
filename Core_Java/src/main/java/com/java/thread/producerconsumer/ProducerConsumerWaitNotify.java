//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.producerconsumer;

import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumerWaitNotify {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException {

		int value = 0;

		while (true) {

			synchronized (lock) {
				while (list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}

		}
	}

	public void consume() throws InterruptedException {

		final Random random = new Random();

		while (true) {
			synchronized (lock) {
				while (list.size() == 0) {
					lock.wait();
				}
				System.out.print("List size is: " + list.size());
				final int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}

	public static void main(final String[] args) {
		final ProducerConsumerWaitNotify producerConsumerWaitNotify = new ProducerConsumerWaitNotify();
		producerConsumerWaitNotify.main();
	}

	public void main() {
		final Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					produce();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		final Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					consume();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

	}
}
