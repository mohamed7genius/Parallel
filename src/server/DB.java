package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import client.ReservationDetails;

public class DB {
    public static List<Dictionary> UsersQuery(String query) {
        // MySql Database Connection      
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_fcih-db?characterEncoding=UTF-8",
                    "freedb_new-user","yeFnSh&NdEEM3R3");
            // Success Connection
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            List<Dictionary> results = new LinkedList<Dictionary>();
            while(rs.next()) {
                Dictionary result = new Hashtable();
                result.put("firstName", rs.getString("firstName"));
                result.put("lasttName", rs.getString("lastName"));
                result.put("birthDate", rs.getString("birthDate"));
                result.put("phoneNumber", rs.getString("phoneNumber"));
                result.put("email", rs.getString("email"));
                result.put("password", rs.getString("password"));
                result.put("nationalID", rs.getString("nationalID"));
                result.put("country", rs.getString("country"));
                results.add(result);
            }
            connection.close();
            return(results);
            // Close the connection
        }catch( Exception e ){
            // Error in database
            System.out.println("Error : "+ e);
            JOptionPane.showMessageDialog(null ,"Error in Database Please check your connection!");
            return(null);
        }
    } 
    
    public static List<ReservationDetails> ReservationQuery(String query) {
        // MySql Database Connection      
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_fcih-db?characterEncoding=UTF-8",
                    "freedb_new-user","yeFnSh&NdEEM3R3");
            // Success Connection
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            List<ReservationDetails> results = new LinkedList<ReservationDetails>();
            while(rs.next()) {
                ReservationDetails result = new ReservationDetails(
                        rs.getString("flightSource"),
                        rs.getString("flightDestination"),
                        rs.getString("flightDate"),
                        rs.getString("flightClass"),
                        rs.getInt("seatNumber")
                );
                results.add(result);
            }
            connection.close();
            return(results);
            // Close the connection
        }catch( Exception e ){
            // Error in database
            System.out.println("Error : "+ e);
            JOptionPane.showMessageDialog(null ,"Error in Database Please check your connection!");
            return(null);
        }
    }
    
    public static boolean UpdateQuery(String query) {
        // MySql Database Connection      
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_fcih-db?characterEncoding=UTF-8",
                    "freedb_new-user","yeFnSh&NdEEM3R3");
            // Success Connection
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            connection.close();
            System.out.println("Table updated successfully");
            return(true);
            // Close the connection
        }catch( Exception e ){
            // Error in database
            System.out.println("Error : "+ e);
            return(false);
        }
    }
    public static boolean Login(String query) {
        // MySql Database Connection      
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_fcih-db?characterEncoding=UTF-8",
                    "freedb_new-user","yeFnSh&NdEEM3R3");
            // Success Connection
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                return(true);
            } else{
                return(false);
            }
        }catch( Exception e ){
            // Error in database
            System.out.println("Error : "+ e);
            return(false);
        }
    } 
}
