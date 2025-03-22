package org.example;

import javax.swing.table.JTableHeader;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {


        System.out.println("Main thread Start");

        Thread thread1 = new Thread1("Shadow Clone");
        thread1.start();
//        thread1.setDaemon(true);
        Thread thread2 = new Thread(new Thread2(), "Lightning Clone");
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }, "Mist Clone");
        thread3.start();
        // threads are asynchronous by nature.

        thread2.start();
        Stack s1 = new Stack(100);
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                System.out.println("pushed item " + i);
                s1.push(i);
            }
        }, "Pusher").start();
        new Thread(() ->  {

            for (int i = 0; i < 10; i++){
                int s = s1.pop();
                System.out.println(s);
            }
        }, "Popper").start();



        // thread state
        Thread threadState = new Thread(new Thread( () -> {
            try {
                Thread.sleep(1);
                for(int i = 0; i < 10; i++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread State Demo"));
        threadState.start();

        while (true){
            Thread.State state = threadState.getState();
            System.out.println(state);
            if(Thread.State.TERMINATED == state) break;
        }

        // Thread Join

        Thread threadJoin = new Thread(new Thread( () -> {
            System.out.println(Thread.currentThread());
        }), "Thread Join");

        threadJoin.start();
        try {
            threadJoin.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Dead Lock Situation

        StringBuffer lock1 = new StringBuffer("Lock1");
        StringBuffer lock2 = new StringBuffer("Lock2");

        Thread threadLockDemo1 = new Thread( () -> {
            synchronized (lock1){ // this will get the lock 1 first
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){  // this will fight for the lock 2 which is currently with the thread 2.
                    System.out.println("Lock Acquired");
                }
        }
        }, "Thread Demo 1");

        Thread threadLockDemo2 = new Thread( () -> {
            synchronized (lock2){ // this will get the lock 2 first
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1){ // this will fight for the lock 1 which is currently with the thread 1.
                    System.out.println("Lock Acquired");
                }
            }
        }, "Thread Demo 2");

        threadLockDemo1.start();
        threadLockDemo2.start();

        System.out.println( "Main thread End");
    }
}