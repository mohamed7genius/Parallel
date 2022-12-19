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
    
    public ReservationDetails(String fightSourse, String flightDestination, String flightDate, String flightClass, int seatNumber){
        super(fightSourse, flightDestination, flightDate, flightClass);
        this.seatNumber = seatNumber;
    }
    
    public int getSeatNumber(){
        return this.seatNumber;
    }
    
    @Override
    public void print() {
        System.out.println(this.fightSourse+"\n"+ this.flightDestination+"\n"+
                this.flightDate+"\n"+ this.flightClass+"\n"+ this.seatNumber);
    }
    
}
