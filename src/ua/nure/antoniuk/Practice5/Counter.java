package ua.nure.antoniuk.Practice5;

/**
 * Created by Max on 28.11.2017.
 */
public class Counter {
    private int counter1;
    private int counter2;

    public int getCounter2() {
        return counter2;
    }

    public void setCounter2(int counter2) {
        this.counter2 = counter2;
    }

    public int getCounter1() {

        return counter1;
    }

    public void setCounter1(int counter1) {
        this.counter1 = counter1;
    }

    public void addCounter1(){
        counter1++;
    }

    public void addCounter2(){
        counter2++;
    }
}
