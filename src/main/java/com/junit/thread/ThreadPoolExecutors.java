package com.junit.thread;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutors {
	public static void main(String[] args) {
		try {
			// In this implementation we are saying store 50 thread and once
			// pool is free use them. if we got more than 50 then we will get
			// java.util.concurrent.RejectedExecutionException. We can use
			// another use other constructor for infinite thread new
			// LinkedBlockingQueue<Runnable>().
			BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>(
					50);
			// if we use new ArrayBlockingQueue<Runnable>(2) in that case we are
			// saying after maximum thread pool we can store two more thread in
			// queue. if got more we will get
			// java.util.concurrent.RejectedExecutionException
			// BlockingQueue<Runnable> blockingQueue = new
			// ArrayBlockingQueue<Runnable>(2);

			// ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long
			// keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
			// ThreadFactory threadFactory)
			ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
					4, 5000, TimeUnit.MILLISECONDS, blockingQueue,
					new SimpleThreadFactory());

			File folder = new File("C:\\BigFiles\\ThreadData");
			for (final File fileEntry : folder.listFiles()) {
				if (!fileEntry.isDirectory()) {
					final Runnable worker = new ReadFileThread(
							fileEntry.getAbsolutePath());
					threadPoolExecutor.execute(worker);
				}

			}
			while (!blockingQueue.isEmpty()) {
				System.out.println(blockingQueue.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class ReadFileThread implements Runnable {
	String fileName;

	public void run() {
		System.out.println("Start reading file " + this.fileName);
		final File file = new File(this.fileName);
		final long len = file.length();
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(file, "rw");
			raf.setLength(len);
			final FileChannel chan = raf.getChannel();

			final long t0 = System.currentTimeMillis();

			final List<MappedByteBuffer> maps = new ArrayList<MappedByteBuffer>();

			long off = 0;
			while (off < len) {
				final long chunk = Math.min(len - off, Integer.MAX_VALUE);
				MappedByteBuffer map;
				map = chan.map(MapMode.READ_WRITE, off, chunk);

				off += map.capacity();
				maps.add(map);
			}
			for (final MappedByteBuffer mappedByteBuffer : maps) {
				mappedByteBuffer.load();
				for (int i = 0; i < mappedByteBuffer.limit(); i++) {
					// System.out.print((char) mappedByteBuffer.get());
				}
				mappedByteBuffer.clear();
			}
			raf.close();

			final long t1 = System.currentTimeMillis();

			System.out.println("End file reading " + this.fileName
					+ " It tooks: " + (t1 - t0) + "ms");
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	public ReadFileThread(final String fileName) {
		this.fileName = fileName;
	}
}

class SimpleThreadFactory implements ThreadFactory {
	public Thread newThread(Runnable runner) {
		Thread t = new Thread(runner);
		t.setDaemon(true);
		t.setPriority(Thread.MIN_PRIORITY);
		return t;
	}
}
