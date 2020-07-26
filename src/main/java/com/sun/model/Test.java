package com.sun.model;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 */
public class Test {
    public static void main(String[] args) {
        String lock1 = "aa";
        String lock2 = "bb";
        new Thread(new Testq(lock1,lock2),"a").start();
        new Thread(new Testq(lock2,lock1),"b").start();
    }
}
class Testq implements Runnable{
    private String lock1="aa";
    private String lock2="bb";

    public Testq() {
    }

    public  Testq(String lock1, String lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {

        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+"=a我还要b=>"+lock2);
            try{TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e) { e.printStackTrace(); } ;
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+"=b我我要a==>"+lock1);
            }
        }
    }
}