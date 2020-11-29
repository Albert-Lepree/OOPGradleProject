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
import java.sql.SQLException;
import java.sql.Statement;


public class loginController {

  @FXML
  private TextField username;

  @FXML
  private PasswordField password;

  @FXML
  private Label passIncorrect;

  @FXML
  public void loginButton(ActionEvent event) throws Exception {

    boolean passwordCorrect = false;

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/resources";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = '" + username.getText() + "'";

      System.out.println(sql);


        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
          String thePass = rs.getString(3);
          String flippedPass = Employee.reverseString(thePass);
          String inputtedPassword = password.getText();

          if (flippedPass.equals(inputtedPassword)) {
            passwordCorrect = true;

          }
        }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
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

  @FXML
  public void newUserButton(ActionEvent event) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("newEmployee.fxml"));

    Scene scene = new Scene(root, 597, 400);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(scene);
    window.show();
  }


}
