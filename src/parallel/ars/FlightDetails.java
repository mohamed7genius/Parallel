package parallel.ars;

import java.io.Serializable;


public class FlightDetails implements Serializable {
    
    protected String fightSourse;
    protected String flightDestination;
    protected String flightDate;
    protected String flightClass; 

    public FlightDetails(String fightSourse, String flightDestination, String flightDate, String flightClass) {
 
        this.fightSourse = fightSourse;
        this.flightDestination = flightDestination;
        this.flightDate = flightDate;
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

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }
    
    public void print(){
        System.out.println(this.fightSourse+"\n"+ this.flightDestination+"\n"+
                this.flightDate+"\n"+ this.flightClass+"\n");
    
    }
    
    
}
