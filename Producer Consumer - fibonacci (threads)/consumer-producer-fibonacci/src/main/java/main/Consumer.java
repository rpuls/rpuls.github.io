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
public class Consumer extends Thread {

    BlockingQueue<Integer> s2;

    public Consumer(BlockingQueue<Integer> s2) {
        this.s2 = s2;

    }

    @Override
    public void run() {
        try {
            int sum = 0;
            while (Main.producerCount.get() > 0) { //possible race condition here!
                int i = s2.take();
                System.out.println(i);
                sum+=i;
                //Thread.sleep(1000); this will show problems
            }
            System.out.println("sum: "+sum);
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
