package ua.nure.antoniuk.Practice5;

import java.util.concurrent.locks.Lock;

/**
 * Created by Max on 28.11.2017.
 */
public class Part3 extends Thread{
    private Counter counter;

    Part3(Counter c) {
        counter = c;
    }

    /*@Override
    public void run() {
        while (true) {
            System.out.println(getName() + " " + (counter.getCounter1() == counter.getCounter2()));
            counter.addCounter1();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter.addCounter2();
        }
    }*/

    @Override
    public void run() {
        while (true){
            synchronized (counter){
                System.out.println(getName() + " " + (counter.getCounter1() == counter.getCounter2()));
                counter.addCounter1();
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter.addCounter2();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Part3(counter);
        Thread t2 = new Part3(counter);
        Thread t3 = new Part3(counter);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
