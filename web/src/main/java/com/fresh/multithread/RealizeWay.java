package com.fresh.multithread;

/**
 * Created by ljiajiali on 17-9-14.
 */
public class RealizeWay {
    public static void main(String[] args) {
        //主线线程启动三个子线程，每个子线程都会卖出10张票
        MyThread t1 = new MyThread("1");
        MyThread t2 = new MyThread("2");
        MyThread t3 = new MyThread("3");
        t1.start();
        t2.start();
        t3.start();

        //主线程创建3个子线程，但一共卖出10张票，因为三个线程是基于一个myRunableThread创建的
        //三个子线程共享一个MyRunableThread接口
        MyRunableThread myRunableThread = new MyRunableThread();
        Thread t4 = new Thread(myRunableThread, "a");
        Thread t5 = new Thread(myRunableThread, "b");
        Thread t6 = new Thread(myRunableThread, "c");
        t4.start();
        t5.start();
        t6.start();

        //start和run的区别(可以去看看源码的实现)
        MyThread2 t7 = new MyThread2("1");
        System.out.println(Thread.currentThread().getName() + " running");
        t7.run();
        System.out.println(Thread.currentThread().getName() + " running");
        t7.start();
    }
}

class MyThread extends Thread {
    private int ticket = 50;
    MyThread(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " remain ticket count:" + ticket--);
            }
        }
    }
}

class MyThread2 extends Thread {
    private int ticket = 50;
    MyThread2(String name) {
        super(name);
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running");
    }
}

class MyRunableThread implements Runnable {
    private int ticket = 50;
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " remain ticket count:" + ticket--);
            }
        }
    }
}
