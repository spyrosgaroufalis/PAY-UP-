import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.Connection;

public class payPhone {  
       static Connection connection;
       static IndexPage indexP;

    static void myAdd(ActionEvent e){
     Object o = e.getSource();
     
      {
             String payment_num = dropDown();//δεν σωστη η δηλωση, υποτιθεται πως το pay_num να παιρνει την τιμη του απο το dropDown μενου στην indexpage
                 
            
             try {
                 connection = DatabaseConnection.getConnection();
                 String storedProcedure = "{call lastest_date(?)}";
                 CallableStatement stmt = connection.prepareCall(storedProcedure);
                 stmt.setString(1, payment_num);
                 stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
                 stmt.execute();
                 String outputParameter1 = stmt.getString(2);

                 if (outputParameter1 == "true") {
                     // Login successful, proceed to index page
                     String amount = PayPhoneBill.t3.getText();
                    // card();
                     indexP = new IndexPage();
                     PayElectricBill.frame.dispose(); // Close the login page
                 } else {
                     // Login failed, display an error message
                     System.out.println("Ανεπιτυχής Πληρωμή");
                     PopupMessage popup = new PopupMessage(LogInPage.frame,"Ο αριθμός παροχής με τον αριθμό πληρωμής δεν αντιστοιχούν");
                 }

             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
            
         }
}
}