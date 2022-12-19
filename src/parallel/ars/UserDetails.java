package parallel.ars;

import java.io.Serializable;

public class UserDetails implements Serializable {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String nationalID;
    protected String birthDate;
    protected String country;
    protected String phoneNumber;
    
    public UserDetails(String firstName, String lastName, String email, String password, String nationalID, String birthDate, String country, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.nationalID = nationalID;
        this.birthDate = birthDate;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getNationalID(){
        return this.nationalID;
    }
    
    public String getBirthDate(){
        return this.birthDate;
    }
    
    public String getCountry(){
        return this.country;
    }
    
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
}
