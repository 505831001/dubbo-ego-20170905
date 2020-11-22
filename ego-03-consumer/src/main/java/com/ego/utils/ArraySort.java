package com.ego.utils;

/**
 * @author Administrator
 */
public class ArraySort implements Runnable {

    private int number;

    public ArraySort(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        int[] array = new int[]{102, 338, 62, 9132, 580, 666};
        for (int n : array) {
            new Thread(new ArraySort(n)).start();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.number);
            System.out.println(this.number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
