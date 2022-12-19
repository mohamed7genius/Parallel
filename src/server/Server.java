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
        
        /*DB.UpdateQuery("Create Table flights(id INT(64) NOT NULL AUTO_INCREMENT , userEmail VARCHAR(50) NOT NULL, fightSourse VARCHAR(50) NOT NULL, flightDestination VARCHAR(50) NOT NULL, flightDate VARCHAR(50) NOT NULL, flightClass VARCHAR(50) NOT NULL, seatNumber VARCHAR(50) NOT NULL, PRIMARY KEY (ID))");
        DB.UpdateQuery("INSERT INTO flights (userEmail, fightSourse, flightDestination, flightDate, flightClass, seatNumber )" +
                "VALUES ('mohamed@fcih.com', 'Egypt', 'Iraq', '2022/12/19', 'Economy', '1');");
        DB.UpdateQuery("INSERT INTO flights (userEmail, fightSourse, flightDestination, flightDate, flightClass, seatNumber )" +
                "VALUES ('mohamed@fcih.com', 'Egypt', 'Iraq', '2022/12/19', 'Economy', '2');");
        DB.UpdateQuery("INSERT INTO flights (userEmail, fightSourse, flightDestination, flightDate, flightClass, seatNumber )" +
                "VALUES ('mohamed@fcih.com', 'Egypt', 'Iraq', '2022/12/19', 'Economy', '3');");
        DB.UpdateQuery("INSERT INTO flights (userEmail, fightSourse, flightDestination, flightDate, flightClass, seatNumber )" +
                "VALUES ('mohamed@fcih.com', 'Egypt', 'Qatar', '2022/12/20', 'Economy', '4');");*/
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
