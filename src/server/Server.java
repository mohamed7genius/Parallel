/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moham
 */
public class Server {
    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;
        Socket socket = null;
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            serverSocket = new ServerSocket(5987);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("New Socket" + socket);
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            executor.execute(new threads(socket));
        }
        
        /*ExecutorService executor = Executors.newFixedThreadPool(2);
        
        Runnable thread1 = new threads(1);
        executor.execute(thread1);
        
        Runnable thread2 = new threads(2);
        executor.execute(thread2);
        
        Runnable thread3 = new threads(3);
        executor.execute(thread3);
        
        
        /*ExecutorService executor = Executors.newFixedThreadPool(2);
        
        Runnable thread1 = new threads(1);
        executor.execute(thread1);
        
        Runnable thread2 = new threads(2);
        executor.execute(thread2);
        
        Runnable thread3 = new threads(3);
        executor.execute(thread3);
        
        
        /*ExecutorService executor = Executors.newFixedThreadPool(2);
        
        Runnable thread1 = new threads(1);
        executor.execute(thread1);
        
        Runnable thread2 = new threads(2);
        executor.execute(thread2);
        
        Runnable thread3 = new threads(3);
        executor.execute(thread3);
        
        
        /*ExecutorService executor = Executors.newFixedThreadPool(2);
        
        Runnable thread1 = new threads(1);
        executor.execute(thread1);
        
        Runnable thread2 = new threads(2);
        executor.execute(thread2);
        
        Runnable thread3 = new threads(3);
        executor.execute(thread3);
        
        executor.shutdown();*/
    }
}
