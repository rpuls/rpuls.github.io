/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rasmus
 */
public class Main {

    public static AtomicInteger producerCount = new AtomicInteger(4);

    public static void main(String[] args) {

        try {
            Integer[] ints = {4, 5, 8, 12, 21, 22, 34, 35, 36, 37, 42};
            ArrayBlockingQueue<Integer> s1 = new ArrayBlockingQueue(ints.length);
            ArrayBlockingQueue<Integer> s2 = new ArrayBlockingQueue(ints.length);
            ArrayList<Producer> producerThreads = new ArrayList();
            
            for (Integer i : ints) {
                s1.put(i);
            }
            for (int i = 0; i < producerCount.get(); i++) {
                Producer p = new Producer(s1, s2);
                p.start();
                producerThreads.add(p);
            }
            Consumer c1 = new Consumer(s2);
            c1.start();

            c1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
