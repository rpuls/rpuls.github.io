/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rasmus
 */
public class Producer extends Thread {

    BlockingQueue<Integer> s1;
    BlockingQueue<Integer> s2;

    public Producer(BlockingQueue<Integer> s1, BlockingQueue<Integer> s2) {
        this.s1 = s1;
        this.s2 = s2;

    }

    @Override
    public void run() {
        for (Integer i = s1.poll(); i!=null; i = s1.poll()){
            int f = fib(i);
            try {
                s2.put(f);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Main.producerCount.getAndDecrement();
    }

    private int fib(int n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
