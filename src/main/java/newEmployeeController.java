import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.stage.Stage;

public class newEmployeeController {

  @FXML
  private TextField fullName;

  @FXML
  private TextField Username;

  @FXML
  private TextField Email;

  @FXML
  private PasswordField password;

  /*------------------------------------------------------
    createNewAccount:
    takes the values entered and adds an employee to the
    database using the values, so that the user may login.
  --------------------------------------------------------*/
  @FXML
  void createNewAccount(ActionEvent event) throws IOException {

    String passwordToInsert = Employee
        .reverseString(password.getText()); // reverses the password for security

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/resources";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn;
    Statement stmt;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute an update
      stmt = conn.createStatement();

      String insertSQL =
          "INSERT INTO EMPLOYEE(USERNAME, NAME, PASSWORD, EMAIL_ADDRESS) VALUES ( '" + Username
              .getText() + "', '" + fullName.getText()
              + "', '" + passwordToInsert + "' , '" + Email.getText() + "' );";

      stmt.executeUpdate(insertSQL);

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

    Scene scene = new Scene(root, 597, 400);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(scene);
    window.show();

  }
}




