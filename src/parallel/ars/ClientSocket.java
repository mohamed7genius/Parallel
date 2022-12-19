package parallel.ars;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Constants;

public class ClientSocket {
    private static Socket socket = null;
    private static ObjectOutputStream outObj;
    private static ObjectInputStream inObj;
    
    public static void InitSocket(){
        if ( socket == null ) {
            try {
                //establish socket connection to server
                socket = new Socket("localhost", Constants.getPortNumber());
            } catch (IOException ex) {
                Logger.getLogger(ParallelARS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if ( outObj == null ) {
            try {
                outObj = new ObjectOutputStream (socket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if ( inObj == null ) {
            try {
                inObj = new ObjectInputStream (socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void SendMessage(Object message){
        ClientSocket.InitSocket();
        try {
            System.out.println("Socket sendMessage " + socket);
            
            outObj.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Object ReceiveMessage(){
        try {
            ClientSocket.InitSocket();
            System.out.println("Socket receiveMessage  " + socket);
            return inObj.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}