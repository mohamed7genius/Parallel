package parallel.ars;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Dictionary;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParallelARS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Keep it so we can create another database locally
        // DB.UpdateQuery("Create Table users(id INT(64) NOT NULL AUTO_INCREMENT , firstName VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, birthDate VARCHAR(50) NOT NULL, phoneNumber VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, nationalID VARCHAR(50) NOT NULL, country VARCHAR(50) NOT NULL, PRIMARY KEY (ID))");
        //DB.UpdateQuery("INSERT INTO users (firstName, lastName, birthDate, phoneNumber, email, password, nationalID, country )" +
                   // "VALUES ('Mohamed', 'Ashraf', '2022/12/5', '+201000000000', 'mohamed@fcih.com', '123456789', '0123456789012', 'Egypt' );");
        
        // Example on how to get any data from database
        /*List<Dictionary> users = DB.UsersQuery("Select * from users");
        System.out.println(users.get(0).get("firstName"));*/
        
<<<<<<< HEAD
        new StartPage().setVisible(true);
=======
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        for(int i=0; i<5;i++){
            try {
                //establish socket connection to server
                socket = new Socket("localhost", 5987);
                //write to socket using ObjectOutputStream
                oos = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Sending request to Socket Server");
                if(i==4)oos.writeObject("exit");
                else oos.writeObject(""+i);
                //read the server response message
                ois = new ObjectInputStream(socket.getInputStream());
                String message = null;
                try {
                    message = (String) ois.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ParallelARS.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Message: " + message);
                //close resources
                ois.close();
                oos.close();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ParallelARS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(ParallelARS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
>>>>>>> 241e868 (Init threads and socketapi)
    }
    
}
