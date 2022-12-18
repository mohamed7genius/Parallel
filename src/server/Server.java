package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author moham
 */
public class Server {
    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;
        Socket socket = null;
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);

        try {
            serverSocket = new ServerSocket(5987);
            System.out.println("Server is started!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is listening for requests ...");
        
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("New Socket" + socket);
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            executorService.execute(new threads(socket));
        }
        
        // executorService.shutdown();
    }
}
