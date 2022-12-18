package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Dictionary;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class threads implements Runnable {
    protected Socket socket;

    public threads(Socket socket){
        this.socket = socket;
    }
    
    public Socket getSocket(){
        return this.socket;
    }

    @Override
    public void run() {
        System.out.println("This is socket : " + getSocket() + " and is handled by thread : " + Thread.currentThread().getName() );
        ObjectInputStream  inObj;
        ObjectOutputStream outObj;
        try {
            inObj = new ObjectInputStream (socket.getInputStream());
            outObj = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        while (true) {
            try {
                var newObj = inObj.readObject();
                if ((newObj == null)) {
                    System.out.println("QUIT!");
                    socket.close();
                    return;
                } else if ( newObj instanceof String string ) { // Login
                    String [] loginDetails = string.split(";");
                    if ( loginDetails.length != 2 ) {
                        outObj.writeObject(false);
                    }
                    String loginEmail = loginDetails[0]; 
                    String loginPassword = loginDetails[1];
                    outObj.writeObject(Login(loginEmail, loginPassword));
                }
            } catch (Exception e) {
                System.out.println("Server Error: " + e);
                return;
            }
        }
    }
    
    private static Boolean Login(String email, String password){
        String sql = "select * from users where email = '"+email+"' and password = '"+password+"'";
        List<Dictionary> users = DB.UsersQuery(sql);
        return users != null && users.size() == 1;
    }
}
