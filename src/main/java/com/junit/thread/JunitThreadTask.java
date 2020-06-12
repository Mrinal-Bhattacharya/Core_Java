package com.junit.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JunitThreadTask implements Callable<Boolean>{

	public static Future<Boolean> subscribe(JunitThreadTask task) {
		ExecutorService executors=Executors.newSingleThreadExecutor();
		Future<Boolean> future=executors.submit(new JunitThreadTask());
		return future;
	}

	private void handlePrintData() {
		for (int i = 0; i < 20; i++) {
			System.out.println("print ln");			
		}
	}

	@Override
	public Boolean call() throws Exception {
		handlePrintData();
		return true;
	}

}
