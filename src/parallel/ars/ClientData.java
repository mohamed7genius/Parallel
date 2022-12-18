package parallel.ars;

public class ClientData {
    private static String userEmail;
    public static void setEmail(String email){
        userEmail = email;
    }
    
    public static String getEmail(){
        return userEmail;
    }
}
