package org.example;

public class Thread1 extends Thread{


    public  Thread1(String s) {
        super(s);
    }

    @Override
    public void run(){
         for (int i = 0; i < 10; i++){
             StringBuilder s = new StringBuilder(this.getName());
             System.out.println(s + " " + i);
         }
    }
}
