package org.example;

public class Thread2 implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            StringBuffer s = new StringBuffer(Thread.currentThread().getName());
            System.out.println(s + " " + i);
        }
    }
}
