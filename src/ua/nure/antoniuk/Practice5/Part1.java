package ua.nure.antoniuk.Practice5;

/**
 * Created by Max on 28.11.2017.
 */
public class Part1 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Part1().getThread1();
        t1.start();
        t1.join();
        System.out.println("~~~~~~~~");
        Thread t = new Thread(new Part1().getThreadRun());
        t.start();
        t.join();
    }

    private MyThread getThread1(){
        return new MyThread();
    }

    private class MyThread extends Thread{
        @Override
        public void run() {
            for(int i = 0; i < 6; i++) {
                System.out.println(getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class MyThreadRun implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 6; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Runnable getThreadRun(){
        return new MyThreadRun();
    }

}
