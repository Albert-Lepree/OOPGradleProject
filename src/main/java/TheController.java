import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.sql.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TheController {

  final String JDBC_DRIVER = "org.h2.Driver";
  final String DB_URL = "jdbc:h2:./res/resources";

  //  Database credentials
  final String USER = "";
  final String PASS = "";
  Connection conn = null;
  Statement stmt = null;


  @FXML
  private TextField txtProductInput;

  @FXML
  private TextField txtManufacturerInput;

  @FXML
  private ChoiceBox<String> cmbType;

  @FXML
  private ComboBox<String> cmbQuantity;

  @FXML
  private TextArea txtAreaProdLog;

  @FXML
  private TableView<Product> prodTable;

  @FXML
  private TableColumn<?, ?> nameCol;

  @FXML
  private TableColumn<?, ?> manufacturerCol;

  @FXML
  private TableColumn<?, ?> typeCol;

  @FXML
  private ListView<String> produceList;

  ObservableList<Product> productArray = FXCollections.observableArrayList();
  ObservableList<String> productNames = FXCollections.observableArrayList();

  void printProdList() {
    for (Product item : productArray) {
      productNames.add(item.getName());
    }
    produceList.setItems(productNames);
  }

  void columns() {
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    manufacturerCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    typeCol.setCellValueFactory(new PropertyValueFactory("type"));

    prodTable.setItems(productArray);
  }

  void setAreaProdlog() {
    Product test = new AudioPlayer("testy", "testy", "test", "test");
    ProductionRecord test1 = new ProductionRecord(test, 3);
    System.out.println(test1.toString());
    System.out.println(test1.getSerialNum());

    txtAreaProdLog.setText(test1.toString());
  }

  void openConnection() {
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: insert a statement
      stmt = conn.createStatement();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  void closeConnection() {
    try {
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /*---------------------------------------------------
  Addproduct:
  Adds a product to the data base using data from the
  text fields in the product line tab
   ---------------------------------------------------*/
  @FXML
  void addProduct(ActionEvent event) {

    try {
      openConnection();
      String product = txtProductInput.getText();
      String manufacturer = txtManufacturerInput.getText();
      String type = cmbType.getValue();

      Product p1 = new Widget(product, manufacturer,
          ItemType.valueOf(type)); // creates product widget using the values from gui

      productArray.add(p1); // adds value to the array

      String insertSql =
          "INSERT INTO Product(type, manufacturer, name) VALUES ( '" + type + "', '" + manufacturer
              + "', '" + product + "' );";

      stmt.executeUpdate(insertSql);

      closeConnection();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /*---------------------------------------------------
  OutputProducts:
  Gets the data from the database and outputs it to
  the console when called
  ---------------------------------------------------*/
  public void outputProducts() {

    try {

      String sql = "SELECT * FROM PRODUCT";

      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("\n\n");
      System.out.println("ITEM " + " TYPE " + " MANUFACTURER");
      while (rs.next()) {
        System.out.println(rs.getString(2) + " " + rs.getString(4) + " " + rs
            .getString(3)); // output data from database to console

        productArray
            .add(new Widget(rs.getString(2), rs.getString(4), ItemType.valueOf(rs.getString(3))));

      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /*---------------------------------------------------
  ComboBox:
  used to add values 1-10 to the combobox
  this will eventually be used in the produce tab
  ---------------------------------------------------*/
  public void comboBox() {
    for (int i = 1; i <= 10; i++) { // loops 10 times
      cmbQuantity.getItems()
          .add(String.valueOf(i)); // converts int i to a string and adds it to the combo box
    }
    cmbQuantity.setValue("1");
    cmbQuantity.setEditable(true);

  }

  /*---------------------------------------------------
  cmbBoxType:
  Used to populate the item type box in the product
  line tab.
  ---------------------------------------------------*/
  public void cmbBoxType() {
    for (ItemType it : ItemType.values()) {
      cmbType.getItems().add(it.toString());
    }
    cmbType.setValue("Select Type");
  }

  /*---------------------------------------------------
  widgetTest:
  A test method used to test the Enum class ItemType
  only functionality is to add a literal to the database
  using the enum other extended classes
  ---------------------------------------------------*/
  public void widgetTest() {
    Product a1 = new Widget("Fire stick", "Amazon", ItemType.AUDIO);
    System.out.println(a1.toString());

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

      String product = a1.name;
      String manufacturer = a1.manufacturer;
      String type = a1.type.getiType();

      String insertSql =
          "INSERT INTO Product(type, manufacturer, name) VALUES ( '" + type + "', '" + manufacturer
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

  /*---------------------------------------------------
  testMultimedia:
  test method to demonstrate the functionality of
  user interface.
  ---------------------------------------------------*/
  public static void testMultimedia() {
    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
        "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }

  /*---------------------------------------------------
  intitialize:
  will always be called, essentially the main method
  of the controller.
  ---------------------------------------------------*/
  public void initialize() {
    openConnection();
    comboBox(); // populates values 1 - 10 in the combobox
    outputProducts(); // outputs data to console
    cmbBoxType();
    setAreaProdlog();
    printProdList();
    columns();
    closeConnection();

  }

}


