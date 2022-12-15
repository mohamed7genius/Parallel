package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
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
        System.out.println("This is socket : " + getSocket() + " Is handled by thread :  " + Thread.currentThread().getName() );
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    out.writeBytes(line + "\n\r");
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
