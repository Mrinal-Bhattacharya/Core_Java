//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package threadTest;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadFilesMultiThreadExec {
    public static void main(final String[] args) {
        final ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 3; i++) {
            final Runnable worker =
                    new ReadFileThread("C:\\files_Big\\File" + i + ".dat");
            executor.execute(worker);
        }
        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();
        // Wait until all threads are finish

        System.out.println("Finished all threads");
    }
}

class ReadFileThread implements Runnable {
    String fileName;

    public void run() {
        final File file = new File(this.fileName);
        final long len = file.length();
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(file, "rw");
            raf.setLength(len);
            final FileChannel chan = raf.getChannel();

            final long t0 = System.currentTimeMillis();

            final List<MappedByteBuffer> maps =
                    new ArrayList<MappedByteBuffer>();

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
                    System.out.print((char)mappedByteBuffer.get());
                }
                mappedByteBuffer.clear();
            }
            raf.close();

            final long t1 = System.currentTimeMillis();

            System.out.println("took: " + (t1 - t0) + "ms");
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public ReadFileThread(final String fileName) {
        this.fileName = fileName;
    }
}
