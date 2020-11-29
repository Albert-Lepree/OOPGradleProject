import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class loginController {

  @FXML
  private TextField username;

  @FXML
  private PasswordField password;

  @FXML
  private Label passIncorrect; // label to be set to visible if pass is incorrect

  /*------------------------------------------------------
    loginButton:
    code to be executed when the login button is hit
    checks the database to see if user/pass is correct
  --------------------------------------------------------*/
  @FXML
  public void loginButton(ActionEvent event) throws Exception {

    boolean passwordCorrect = false;

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

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = '" + username.getText() + "'";

        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
          String thePass = rs.getString(3);
          String flippedPass = Employee.reverseString(thePass);
          String inputtedPassword = password.getText();

          TheController.employee = new Employee(rs.getString(2), rs.getString(1),
              flippedPass, rs.getString(4));

          if (flippedPass.equals(inputtedPassword)) {
            passwordCorrect = true;

          }
        }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    if (passwordCorrect) {
      Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

      Scene scene = new Scene(root, 597, 400);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      window.setScene(scene);
      window.show();


    } else {
      passIncorrect.setVisible(true);
    }
  }

  /*------------------------------------------------------
    newUserButton:
    changes scene to the "newEmployee.fxml" so that the
    user can creat a new account
  --------------------------------------------------------*/
  @FXML
  public void newUserButton(ActionEvent event) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("newEmployee.fxml"));

    Scene scene = new Scene(root, 597, 400);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(scene);
    window.show();
  }

}
