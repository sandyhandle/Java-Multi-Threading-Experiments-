package org.example;

public class Stack {
    int[] stack;
    int head;
    String lock;

    public Stack(int num) {
        this.stack = new int[num];
        this.head = -1;
        lock = new String();
    }

    /**
     * Push method to add an item to the stack.
     * <p>
     * This method is synchronized to ensure that only one thread
     * can execute it at a time for the same Stack instance.
     * Synchronization prevents race conditions when multiple threads
     * try to push items concurrently.
     */
    public synchronized boolean push(int val) {
        if (isFull()) {
            return false;
        }
        ++head;
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
        }

        StringBuffer stringBuffer = new StringBuffer("Push Head ");
        stringBuffer.append(head);

        System.out.println(stringBuffer);
        stack[head] = val;
        return true;

    }

    /**
     * Pop method to remove an item from the stack.
     * <p>
     * This method is also synchronized, ensuring that no two threads
     * can execute push() and pop() on the same instance at the same time.
     * This prevents inconsistent state or data corruption in the stack.
     */
    public synchronized int pop() {
        if (isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            return Integer.MIN_VALUE;
        }
        int val = stack[head];
        stack[head] = -1;
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        StringBuffer stringBuffer = new StringBuffer(" Pop Head ");
        stringBuffer.append(head);

        System.out.println(stringBuffer);
        head--;
        return val;

    }

    public boolean isEmpty() {
        return head < 0;
    }

    public boolean isFull() {
        return head >= stack.length - 1;
    }
}
