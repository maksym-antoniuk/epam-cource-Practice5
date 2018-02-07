package ua.nure.antoniuk.Practice5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by Max on 28.11.2017.
 */
public class Spam {
    private long[] times;
    private String[] messages;
    private Thread[] threads;
    private static final InputStream STD_IN = System.in;
    private static final String ENCODING = "Cp1251";

    public void start(){
        threads = new Thread[messages.length];
        for(int i = 0; i < messages.length; i++) {
            threads[i] = new MyThread(times[i], messages[i]);
            threads[i].start();
        }
    }

    public void stop(){
        for(int i = 0; i < messages.length; i++) {
            threads[i].interrupt();

        }
    }

    public Spam(long[] times, String[] messages){
        this.times = times;
        this.messages = messages;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        Spam spam = new Spam(new long[]{333L, 555L, 777L}, new String[] {"AAAAA", "BBBBB", "CCCCC"});
        spam.start();
        System.setIn(new ByteArrayInputStream(System.lineSeparator().getBytes(ENCODING)));
        Scanner sc = new Scanner(System.in);
        Thread.sleep(1000);
        sc.nextLine();
        spam.stop();
        sc.close();
        System.setIn(STD_IN);
    }

    private class MyThread extends Thread{
        private long time;
        private String s;

        MyThread(long t, String s){
            time = t;
            this.s = s;
        }

        @Override
        public void run() {
            try {
            while (!isInterrupted()) {
                sleep(time);
                System.out.println(s);
            }
            }  catch (InterruptedException e) {
                return;
            }
        }
    }
}
