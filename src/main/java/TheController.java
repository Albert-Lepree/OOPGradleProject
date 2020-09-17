import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import java.sql.*;
import javafx.scene.control.TextField;

public class TheController {


  @FXML
  private TextField txtProductInput;

  @FXML
  private TextField txtManufacturerInput;

  @FXML
  private ChoiceBox<String> cmbType;

  @FXML
  void addProduct(ActionEvent event) {
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

      //STEP 3: insert a statement
      stmt = conn.createStatement();

      String product = txtProductInput.getText();
      String manufacturer = txtManufacturerInput.getText();

      String insertSql =
          "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', '" + manufacturer
              + "', '" + product + "' );";

      stmt.executeUpdate(insertSql);

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void comboBox(){
    for(int i = 1; i<=10; i++){ // loops 10 times
      cmbType.getItems().add(String.valueOf(i)); // converts int i to a string and adds it to the combo box
    }
    cmbType.setValue("1");

  }

  public void outputProducts(){
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

      String sql = "SELECT * FROM PRODUCT";

      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("ITEM " + " TYPE " + " MANUFACTURER");
      while (rs.next()) {
        System.out.println(rs.getString(2) + " " +  rs.getString(3) + " " + rs.getString(4)); // output data from database to console
      }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void initialize() {
    comboBox(); // populates values 1 - 10 in the combobox
    outputProducts(); // outputs data to console
  }

}

