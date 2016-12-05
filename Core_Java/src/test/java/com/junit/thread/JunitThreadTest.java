package com.junit.thread;

import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;

public class JunitThreadTest {
	@Test
	public void testForThreadStart() throws Exception {
		JunitThreadTask task=new JunitThreadTask();
		Future<Boolean>  future=JunitThreadTask.subscribe(task);
		System.out.println(future.getClass());
		while(!future.isDone()){
			
		}
		Assert.assertEquals(true, future.get());
		System.out.println("end");
	}
}
