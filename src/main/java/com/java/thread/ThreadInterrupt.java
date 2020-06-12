package com.java.thread;

public class ThreadInterrupt {
	public static void main(String[] args) {
		Task task=new Task();
		
		Thread t1 = new Thread(()->{
			try {
				task.test();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Runnable can't throw exception that's why u need to do below things
				//Thread.currentThread().interrupt();
			}
		});
		t1.start();
		Thread t2 = new Thread(()->{
			try {
				task.test();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t2.start();
		
		t1.interrupt();
		
		
		
	}
}

class Task {
	Object lock = new Object();

	public void test() throws InterruptedException {
		synchronized (lock) {
			lock.wait();
		}

	}

}