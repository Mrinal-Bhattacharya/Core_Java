//****************************************************************
//* Copyright (c) 2016 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.java.thread.pool;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {

	private List queue = new LinkedList();
	private int limit = 10;

	public BlockingQueue(final int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(final Object item) throws InterruptedException {
		while (this.queue.size() == this.limit) {
			wait();
		}
		this.queue.add(item);
		notifyAll();
	}

	public synchronized Object dequeue() throws InterruptedException {
		while (this.queue.size() == 0) {
			wait();
		}
		Object e = this.queue.remove(0);
		notifyAll();
		return e;
	}

}
