package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

    private Queue<Integer> queue;
    private int cap;

    public BlockingQueue(int val) {
        queue = new LinkedList<>();
        cap = val;
    }

    public synchronized boolean add(int val){
        while (queue.size() == cap){
            try {
                queue.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.add(val);
        queue.notifyAll();
        cap++;
        return true;
    }

    public synchronized int remove() throws InterruptedException {
        while (queue.size() == 0){
            try{
                queue.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int poll = queue.poll();
        cap--;
        queue.notifyAll();
        return poll;
    }
}

