/*---------------------------------------------------
  Albert Finn Lepree
  Note:
  above every method is a comment block to give a
  better idea of what is going on.
---------------------------------------------------*/

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import java.sql.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class TheController {

  /*---------------------------------------------------
    global database fields
  ---------------------------------------------------*/
  final String JDBC_DRIVER = "org.h2.Driver";
  final String DB_URL = "jdbc:h2:./res/resources";

  //  Database credentials
  final String USER = "";
  final String PASS = "";
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs;


  /*---------------------------------------------------
    Product Line tab Attributes
  ---------------------------------------------------*/
  @FXML
  private TextField txtProductInput;

  @FXML
  private TextField txtManufacturerInput;

  @FXML
  private ChoiceBox<String> cmbType;

  @FXML
  private TableView<Product> prodTable;

  @FXML
  private TableColumn<?, ?> nameCol;

  @FXML
  private TableColumn<?, ?> manufacturerCol;

  @FXML
  private TableColumn<?, ?> typeCol;

  /*---------------------------------------------------
    Produce tab Attributes
  ---------------------------------------------------*/
  @FXML
  private ComboBox<String> cmbQuantity;

  @FXML
  private ListView<String> produceList;

  /*---------------------------------------------------
    Production Log tab Attributes
  ---------------------------------------------------*/
  @FXML
  private TextArea txtAreaProdLog;

  /*---------------------------------------------------
    Employee Tab Attributes
  ---------------------------------------------------*/
  @FXML
  private TextArea empTxtArea;

  /*---------------------------------------------------
   Lists to be filled and used throughout the program
   filled with Products and product records taken from
   the database.
  ---------------------------------------------------*/
  ObservableList<String> productNames = FXCollections.observableArrayList();
  ObservableList<Product> productLine = FXCollections.observableArrayList();
  ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  static Employee employee;

  /*---------------------------------------------------
    addToProductionDB:
    gets the values from the produce tab.
    records the produced product to the Database.
  ---------------------------------------------------*/
  @FXML
  void addToProductionDB(ActionEvent event) {
    int item = produceList.getSelectionModel().getSelectedIndex();
    int quantIprod = Integer
        .parseInt(cmbQuantity.getValue()); // gets int from the comboquant box on the produce tab

    System.out.println(ProductionRecord.countOfAudio);

    for (int i = 0; i < quantIprod;
        i++) { // adds productionRecord to database and GUI // loops n(number of items produced) times
      ProductionRecord a1 = new ProductionRecord(
          productLine.get(item)); // creates a production record using the selected item.

      txtAreaProdLog
          .appendText(a1.toString()); // prints the record production to the production log box

      productionRun.add(a1); // adds value to the array

      Timestamp ts = new Timestamp(
          a1.getProdDate().getTime()); // creates a timestamp object using the date from the object

      openConnection();
      try {

        int prodNum = a1.getProductionNum();
        int prodID = a1.getProductID();
        String prodSerialNum = a1.getSerialNum();

        String insertSql =
            "INSERT INTO ProductionRecord(PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) VALUES ( '"
                + prodNum + "', '" + prodID
                + "', '" + prodSerialNum + "', '" + ts + "' );";

        stmt.executeUpdate(insertSql);


      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    closeConnection();

  }

  /*---------------------------------------------------
  addProduct:
  Adds a product to the data base using data from the
  text fields in the product line tab
   ---------------------------------------------------*/
  @FXML
  void addProduct(ActionEvent event) {

    openConnection();

    try {

      String product = txtProductInput.getText();
      String manufacturer = txtManufacturerInput.getText();
      String type = cmbType.getValue();

      Product p1 = new Widget(product, manufacturer,
          ItemType.valueOf(type)); // creates product widget using the values from gui

      productLine.add(p1); // adds value to the array

      String insertSql =
          "INSERT INTO Product(type, manufacturer, name) VALUES ( '" + type + "', '" + manufacturer
              + "', '" + product + "' );";

      stmt.executeUpdate(insertSql);


    } catch (SQLException e) {
      e.printStackTrace();
    }
    produceList.getItems().clear();
    setupProductLineTable();
    closeConnection();
  }

  /*---------------------------------------------------
    showProduction:
    prints the productRecords Array to the ProdLog box
  ---------------------------------------------------*/
  void showProduction() {
    for (ProductionRecord item : productionRun) {
      txtAreaProdLog.appendText(item.toString());
    }
  }

  /*---------------------------------------------------
    setupProductLineTable:
    prints the productArray array to the produce list.
    Enhanced for loop also loads an array of strings.
  ---------------------------------------------------*/
  void setupProductLineTable() {
    for (Product item : productLine) {
      productNames.add(item.toString());
    }
    produceList.setItems(productNames);
  }

  /*---------------------------------------------------
    populateColumns:
    populates the columns in table view with data from
    the array "productArray."
  ---------------------------------------------------*/
  void populateColumns() {
    nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    manufacturerCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    typeCol.setCellValueFactory(new PropertyValueFactory("type"));

    prodTable.setItems(productLine);
  }

  /*---------------------------------------------------
    openConnection:
    opens connection to the data base. (duh)
  ---------------------------------------------------*/
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

  /*---------------------------------------------------
    closeConnection:
    closes connection to the data base.
  ---------------------------------------------------*/
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
  PopulateArrays:
  gets the data from the data base to be used in the gui.
  ---------------------------------------------------*/
  void populateArrays() {

    try {
      // populates the productArray array from the product table in the database
      String sql = "SELECT * FROM PRODUCT";
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        productLine
            .add(new Widget(rs.getString(2), rs.getString(4), ItemType.valueOf(rs.getString(3))));
      }

      // populates the productRecord array from the productionrecords table in the database
      sql = "SELECT * FROM PRODUCTIONRECORD";
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        productionRun
            .add(new ProductionRecord(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4)));

        String serialNum = rs.getString(3);

        // if statement to count types for serial number
        String type = serialNum.substring(3, 5);
        if (type.equals("AM") || type.equals("AU")) {
          ProductionRecord.countOfAudio++;

        } else {

          ProductionRecord.countOfVisual++;
        }

      }


    } catch (SQLException e) {
      e.printStackTrace();
    }
    showProduction(); // prints logs to prodLogs tab
  }

  /*---------------------------------------------------
  ComboBox:
  used to add values 1-10 to the combobox
  this will eventually be used in the produce tab
  ---------------------------------------------------*/
  void comboBox() {
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
  void cmbBoxType() {
    for (ItemType it : ItemType.values()) {
      cmbType.getItems().add(it.toString());
    }
    cmbType.setValue("Select Type");
  }

  /*---------------------------------------------------
  intitialize:
  will always be called, essentially the main method
  of the controller.
  ---------------------------------------------------*/
  public void initialize() {
    openConnection();
    comboBox(); // populates values 1 - 10 in the combobox
    populateArrays(); // populates arrays with data from the data base.
    cmbBoxType(); // populates combobox with ItemTypes
    setupProductLineTable(); // prints the production list
    populateColumns(); // populates columns using arrays.
    empTxtArea.appendText(employee.toString()); // fills the employee tab with information
    closeConnection();


  }

}


