/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parallel.ars;

/**
 *
 * @author moham
 */
public class ReservationDetails extends FlightDetails {
    
    protected int seatNumber;
    protected String userEmail;
    
    public ReservationDetails(String flightSource, String flightDestination, String flightDate, String flightClass, int seatNumber){
        super(flightSource, flightDestination, flightDate, flightClass);
        this.seatNumber = seatNumber;
    }
    
    public void setUserEmail(String email){
        this.userEmail = email;
    }
    
    public int getSeatNumber(){
        return this.seatNumber;
    }
    
    public String getUserEmail(){
        return this.userEmail;
    }
    
    @Override
    public void print() {
        System.out.println(this.flightSource+"\n"+ this.flightDestination+"\n"+
                this.flightDate+"\n"+ this.flightClass+"\n"+ this.seatNumber);
    }
    
}
