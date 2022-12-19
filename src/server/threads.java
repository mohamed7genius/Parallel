package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;
import parallel.ars.FlightDetails;
import parallel.ars.ReservationDetails;
import parallel.ars.UserDetails;

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
                } else if ( newObj instanceof String string ) { // Login or userTickets
                    String [] mainString = string.split(";");
                    if ( "login".equals(mainString[0]) ) {
                        if ( mainString.length != 3 ) {
                            // There's something wrong!
                            outObj.writeObject(false);
                        }
                        String loginEmail = mainString[1]; 
                        String loginPassword = mainString[2];
                        outObj.writeObject(Login(loginEmail, loginPassword));
                    } else if ( "myTickets".equals(mainString[0]) ) {
                        if ( mainString.length != 2 ) {
                            List<ReservationDetails> empty = new LinkedList<>();
                            outObj.writeObject(empty);
                        }
                        // will return user tickets
                        String userEmail = mainString[1];
                        outObj.writeObject(GetUserTickets(userEmail));
                    }  else {
                        // Error!
                        System.out.println("Server String Passed!");
                        outObj.writeObject(false);
                    }
                    
                } else if ( newObj instanceof UserDetails userDetails) {
                    outObj.writeObject(SignUp(userDetails));
                } else if (newObj instanceof ReservationDetails reservationDetails) { 
                    System.out.println("Receive Reservation!");
                    // Get a ticket for this user : here the race condition will happen!
                    outObj.writeObject(ReserveUserFlight(reservationDetails));
                } else if (newObj instanceof FlightDetails flightDetails) { 
                    // Will return a list of available flights for this flight
                    outObj.writeObject(GetAvailableFlights(flightDetails));
                } else {
                    // Nothing matched return 
                    outObj.writeObject(null);
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
    
    private static Boolean SignUp(UserDetails user){
        if ( !user.getEmail().isEmpty()&& !user.getPassword().isEmpty()){
            // Check that this email is not in DB
            String sql = "select * from users where email = '"+user.getEmail()+"';";
            List<Dictionary> users = DB.UsersQuery(sql);
            if ( !users.isEmpty() ) {
                System.out.println("User already signed up" + users.get(0).get("email"));
                return false;
            }
            // Add this user to DB
            DB.UpdateQuery("INSERT INTO users (firstName, lastName, birthDate, phoneNumber, email, password, nationalID, country ) VALUES ('"+
                    user.getFirstName() +"', '"+ user.getLastName()+"', '"+ user.getBirthDate()+"', '"+ user.getPhoneNumber() +"', '"+ user.getEmail()+"', '"+ user.getPassword()+"', '"+ user.getNationalID()+"', '"+ user.getCountry() +"' );");
            return true;
        }
        
        return false;
    }
    
    private static List<ReservationDetails> GetUserTickets(String email){
        String sql = "select * from flights where userEmail = '"+email+"';";
        List<ReservationDetails> data = DB.ReservationQuery(sql);
        return data;
    }
    
    private static List<ReservationDetails> GetAvailableFlights(FlightDetails mainFlight){
        // Get all reserved flights in mainFlight source, dest. and date
        String sql = "Select * from flights where flightSource='" + mainFlight.getFlightSource() + "' and flightDestination ='"
                + mainFlight.getFlightDestination() + "' and flightDate='" + mainFlight.getFlightDate() + "' and flightClass='" + mainFlight.getFlightClass() + "';";
        List<ReservationDetails> resFlights = DB.ReservationQuery(sql);
        
        // Get all reserved seats from reserved flights
        List<Integer> resSeats = new LinkedList<>();
        resFlights.forEach(flight -> {
            resSeats.add(flight.getSeatNumber());
        });
        
        
        // Create a list for available seats : we have only 20 seats in our plane
        List<ReservationDetails> availableFlights = new LinkedList<>();
        for(int i=1;i<=Constants.getNumberOfSeats();i++){
            if ( resSeats.indexOf(i) != -1 ) {
                continue;
            }
            ReservationDetails newAvailableFlight = new ReservationDetails(
                mainFlight.getFlightSource(), mainFlight.getFlightDestination(), mainFlight.getFlightDate(), mainFlight.getFlightClass(), i
            );
            availableFlights.add(newAvailableFlight);
        }
        
        return availableFlights;
    }
    
    private static Boolean ReserveUserFlight(ReservationDetails flight){
        String sql = "Select * from flights where flightSource='" + flight.getFlightSource() + "' and flightDestination ='"
                + flight.getFlightDestination() + "' and flightDate='" + flight.getFlightDate() + "' and flightClass='" + flight.getFlightClass() + "' and seatNumber='" + flight.getSeatNumber() + "';";
        List<ReservationDetails> flights = DB.ReservationQuery(sql);
        
        if ( !flights.isEmpty() ) {
            return false;
        }
        
        // Get the ticket for this user
        DB.UpdateQuery("INSERT INTO flights (userEmail, flightSource, flightDestination, flightDate, flightClass, seatNumber )" +
                "VALUES ('"+ flight.getUserEmail() +"', '"+ flight.getFlightSource()+"', '"+ flight.getFlightDestination()+"', '"+ flight.getFlightDate()+"', '"+ flight.getClass() +"', '"+ flight.getSeatNumber()+"');");
        
        return true;  
    };
}