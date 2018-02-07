package ua.nure.antoniuk.Practice5;

import java.util.Arrays;

/**
 * Created by Max on 28.11.2017.
 */
public class Part4 {
    private int[][] matr;

    public Part4(int[][] matr){
        this.matr = matr;
    }

    public static int[][] generateMatrix(int a, int b, int maxValue) {
        int[][] matrix = new int[a][b];
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int)(Math.random() * maxValue);
            }
        }
        return matrix;
    }

    public static void main(String[] args) throws InterruptedException {
        //int[][] matrixx = generateMatrix(4, 27, 1000);
    	int[][] matr = Util.readMatrix2("part4.txt");
        Part4 part = new Part4(matr);
        int max;
        long before = System.currentTimeMillis();
        max = part.maxMatrix();
        long after = System.currentTimeMillis();
        System.out.println(max);
        System.out.println(after - before);
        before = System.currentTimeMillis();
        max = part.slowMaxMatrix();
        after = System.currentTimeMillis();
        System.out.println(max);
        System.out.println(after - before);
    }

    public int maxMatrix() throws InterruptedException {
        MaxThread[] threads = new MaxThread[matr.length];

        for(int i = 0; i < matr.length; i++) {
            threads[i] = getThread(matr[i]);
            threads[i].start();
        }
        int[] temp = new int[matr.length];
        for(int i = 0; i < matr.length; i++) {
            threads[i].join();
            temp[i] = threads[i].getMax();
        }
        return max(temp);
    }

    public int slowMaxMatrix() throws InterruptedException {
        int[] max = new int[matr.length];
        for(int i = 0; i < matr.length; i++) {
            max[i] = max(matr[i]);
        }
        return max(max);
    }


    public static int max(int[] array) throws InterruptedException {
        int temp = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++) {
            if(temp < array[i]){
                temp = array[i];
            }
            Thread.sleep(1);
        }
        return temp;
    }

    public MaxThread getThread(int[] arr){
        return new MaxThread(arr);
    }

    private class MaxThread extends Thread{
        private int[] array;
        private int max;

        public MaxThread(int[] arr) {
            array = arr;
        }

        @Override
        public void run() {
            try {
                max = max(array);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public int getMax(){
            return max;
        }
    }
}
