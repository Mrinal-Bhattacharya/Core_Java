package com.java.thread.pool;

public class Producer implements Runnable {

	private BlockingQueue sharedQueue;

	public Producer(BlockingQueue sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		int value = 0;
		try {
			while (!Thread.currentThread().isInterrupted()) {

				sharedQueue.enqueue(value);
			}
			value++;
		} catch (InterruptedException e) {
			//it is upto user how he want to terminate the thread.
			//Runnable can't throw exception that's why u need to do below things
			/*e.printStackTrace();*/
			Thread.currentThread().interrupt();
		}
	}

	public void cancel() {
		Thread.currentThread().interrupt();
	}
}
