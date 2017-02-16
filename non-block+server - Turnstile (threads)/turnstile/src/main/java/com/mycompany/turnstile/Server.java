package com.mycompany.turnstile;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    static String ip = "localhost";
    static int port = 8080;
    static AtomicInteger spectatorCount =  new AtomicInteger();
    static ArrayList<Monitor> monitors = new ArrayList();

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }

        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        System.out.println("Server started, listening on " + port + ", bound to " + ip);
        while (true) {
            Socket socket = ss.accept();
            handleClient(socket);
        }
    }

    public static void handleClient(Socket s) {
        try {
            Scanner scan = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            String msg;
            String monitorArg;
            String turnstileArg;
            msg = scan.nextLine();

            if ((turnstileArg = isCmd("T:", msg)) != null) {
                System.out.println("turnstile " + turnstileArg + " connected.");
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            handleTurnstile(s, turnstileArg);
                        } catch (IOException ex) {
                            //handle something
                        }
                    }
                };
                t.start();
            } else if ((monitorArg = isCmd("M:", msg)) != null) {
                System.out.println("Monitor " + monitorArg + " connected.");
                Monitor m = new Monitor(s, monitorArg);
                monitors.add(m);
            } else {
                System.out.println("Illegal command: " + msg);
            }

        } catch (IOException x) {
            System.out.println("blabla");
        }
    }

    private static void broardcast(String msg){
        for(Monitor m : monitors){
            m.message(msg);
        }
    }
    
    private static void handleTurnstile(Socket s, String id) throws IOException {
        Scanner scan = new Scanner(s.getInputStream());
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        String msg;
        String arg;
        while (true) {
            msg = scan.nextLine();
            if ((arg = isCmd("I:", msg)) != null) {
                int sc = spectatorCount.addAndGet(Integer.parseInt(arg));
                broardcast(String.valueOf(sc));
            } else {
                System.out.println("Illegal command: " + msg);
            }
        }
    }


    private static String isCmd(String cmd, String msg) {
        if (msg.startsWith(cmd)) {
            return msg.substring(cmd.length()).trim();
        } else {
            return null;
        }
    }

}
