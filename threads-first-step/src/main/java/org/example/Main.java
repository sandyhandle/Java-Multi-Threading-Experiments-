package org.example;

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






        System.out.println( "Main thread End");
    }
}