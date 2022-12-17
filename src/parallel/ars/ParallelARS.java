package parallel.ars;

import java.util.Dictionary;
import java.util.List;

public class ParallelARS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Keep it so we can create another database locally
        // DB.UpdateQuery("Create Table users(id INT(64) NOT NULL AUTO_INCREMENT , firstName VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, birthDate VARCHAR(50) NOT NULL, phoneNumber VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, nationalID VARCHAR(50) NOT NULL, country VARCHAR(50) NOT NULL, PRIMARY KEY (ID))");
        //DB.UpdateQuery("INSERT INTO users (firstName, lastName, birthDate, phoneNumber, email, password, nationalID, country )" +
                   // "VALUES ('Mohamed', 'Ashraf', '2022/12/5', '+201000000000', 'mohamed@fcih.com', '123456789', '0123456789012', 'Egypt' );");
        
        // Example on how to get any data from database
        List<Dictionary> users = DB.UsersQuery("Select * from users");
        System.out.println(users.get(0).get("firstName"));
        
        new StartPage().setVisible(true);
    }
    
}
