/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.turnstile;

import static com.mycompany.turnstile.Server.handleClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rasmus
 */
public class Monitor { //if this monitor should listen on socket it would have to be a thread

    Socket s;
    String id;

    public Monitor(Socket s, String id) {
        this.s=s;
        this.id=id;
    }


    public void message(String m) {
        try {
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            pw.print(m + "\n");
            pw.flush();
            System.out.println(m);
        } catch (IOException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
