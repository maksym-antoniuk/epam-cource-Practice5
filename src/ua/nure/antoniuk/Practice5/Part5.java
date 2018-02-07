package ua.nure.antoniuk.Practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Max on 28.11.2017.
 */
public class Part5 {
    private File file;
    public static final long STRING_LENGHT = 20L + System.lineSeparator().length();


    public Part5(String path) {
        File file1 = new File(path);
        if (file1.exists()) {
            file1.delete();
        }
        file = new File(path);
    }

    private void mainFunction() throws IOException, InterruptedException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        Thread[] threads = new Thread[10];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = getThread(i, raf);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        raf.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Part5 part = new Part5("part5.txt");
        part.mainFunction();
        System.out.println(Util.getInput("part5.txt"));
    }

    public Thread getThread(int n, RandomAccessFile raf){
        return new MyThread(n, raf);
    }

    private class MyThread extends Thread{
        private int n;
        private final RandomAccessFile raf;
        private long pointer;


        public MyThread(int n, RandomAccessFile raf){
            this.n = n;
            this.raf = raf;
            pointer = n * STRING_LENGHT;
        }

        @Override
        public void run() {
            for(int i = 0; i < 20; i++) {
                synchronized (raf){
                    try {
                        raf.seek(pointer + i);
                        raf.writeBytes(String.valueOf(n));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (raf){
                try {
                    raf.seek(pointer + 20);
                    raf.writeBytes(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
