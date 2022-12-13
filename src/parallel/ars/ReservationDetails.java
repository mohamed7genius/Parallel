/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parallel.ars;


public class ReservationDetails {
    
    private String fightSourse;
    private String flightDestination;
    private String arrivalTime;
    private String flightDate;
    private int seatCouts;
    private String flightClass; 

    public ReservationDetails(String fightSourse, String flightDestination, String arrivalTime, String flightDate, int seatCouts, String flightClass) {
 
        this.fightSourse = fightSourse;
        this.flightDestination = flightDestination;
        this.arrivalTime = arrivalTime;
        this.flightDate = flightDate;
        this.seatCouts = seatCouts;
        this.flightClass = flightClass;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
    
    
 
    public String getFightSourse() {
        return fightSourse;
    }

    public void setFightSourse(String fightSourse) {
        this.fightSourse = fightSourse;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public int getSeatCouts() {
        return seatCouts;
    }

    public void setSeatCouts(int seatCouts) {
        this.seatCouts = seatCouts;
    }
    
    public void print(){
        System.out.println(this.fightSourse+"\n"+ this.flightDestination+"\n"+this.arrivalTime+"\n"+
                this.flightDate+"\n"+ this.flightClass+"\n"+this.seatCouts);
    
    }
    
    
}
