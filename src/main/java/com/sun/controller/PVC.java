package com.sun.controller;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PVC {
    static int m = 0;

    public static void main(String[] args) {
        testCountdownLatch();

        /*testThread();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ArrayList<PVC> objects = new ArrayList<>();
        for (;;) {
            objects.add(new PVC());
        }*/

    }
     static int ii = 0;
    static  AtomicInteger atomicInteger = new AtomicInteger();
    public static void testCountdownLatch() {

        //cn.itcast.aop.datasource.ChooseDataSource;
        int num = 200;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            countDownLatch.countDown();
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("测试线程"+atomicInteger.decrementAndGet());
            }, "消费11111111111111111111111").start();
            System.out.println(atomicInteger.incrementAndGet());
        }
    }
    public static void testCallable(){
        new CyclicBarrier(1);
        CompletableFuture.runAsync(System.out::println);


        FutureTask<Integer> integerFutureTask =
                new FutureTask<Integer>(() -> {
                                    Integer i = 10;
                    int i1 = i + 10;
                    return i1;
                                });
        new Thread(integerFutureTask).start();
        try {
            Integer integer = integerFutureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static void testThread(){

        Object o = new Object();
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o) {
                    if (m > 0) {
                        System.out.println("xfz====11111111111111111111111" + m);
                        m--;
                        o.notify();
                    }else {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        },"消费11111111111111111111111").start();
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o) {
                    if (m > 0) {
                        System.out.println("xfz====22222222222222222" + m);
                        m--;
                        o.notify();
                    }else {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        },"消费222222").start();

        new Thread(()->{
            while (true){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o) {
                    if (m > 0) {
                        System.out.println("xfz====333333333333333333" + m);
                        m--;
                        o.notify();
                    }else {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        },"消费333333333333333").start();

        new Thread(()->{
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o) {
                        if (m < 20) {
                            m++;
                            System.out.println("scz====" + m);
                            o.notify();
                        }else {
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

        },"生产程1").start();

    }

    private static void testpvc() {
        Clerk clerk = new Clerk();

        Consumer consumer = new Consumer(clerk);
        consumer.setName("消费者");
        Producer producer = new Producer(clerk);
        producer.setName("生产者");
        consumer.start();
        producer.start();
    }
}


class Clerk{

    private Integer money = 0;

    public synchronized void getMoney() {
        if(money > 0){
            System.out.println("消费者消费第" + money + "个");
            money--;
            notify();
        }else {
            try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }

        }

    }

    public synchronized void putMoney() {
       if(money < 10){
           money++;
           System.out.println("生产者生产第" + money + "个");
           notify();
       }else {
           try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
       }
    }
}

class Consumer extends Thread{
    private Clerk clerk;

    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者消费");
        while (true){
            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
            clerk.getMoney();
        }
    }
}
class Producer extends Thread{
    private Clerk clerk;

    public Producer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        System.out.println("消费者消费");
        while (true){
            try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            clerk.putMoney();
        }
    }

}