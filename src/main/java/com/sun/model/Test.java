/*
package com.sun.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

interface ProxyInterface{
    void testRun();
}
class TestProxy implements ProxyInterface{

    @Override
    public void testRun() {
        System.out.println("动态代理跑起来了");
    }
}
public class Test {
    public static void main(String[] args) throws Exception {
        TestProxy testProxy = new TestProxy();
        ProxyInterface proxy = (ProxyInterface) proxy(testProxy);
        System.out.println("-------------");
        proxy.testRun();
    }

    //动态代理
    private static Object proxy(Object obj) {
       return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(obj, args);
            }
        });
    }

    private static void testURL() throws UnknownHostException {
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(byName);
        System.out.println(localHost);
        //testThread();
    }

    private static void testThread() {
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
}*/
