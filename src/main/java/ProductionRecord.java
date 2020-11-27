import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/*---------------------------------------------------
ProcutionRecord Class:
intended to record things about the prouct such as
serial number, date created, id number, etc.
this class contains constructors and accessors/
mutators for those values.
---------------------------------------------------*/
public class ProductionRecord {

  static int countOfAudio;
  static int countOfVisual;

  private int productionNumber = 0;
  private static int prodNumCount; // I had to make a second prod num count because the static variable was being odd while calling two different types of constructors
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  ProductionRecord(Product product) {
    this.productID = product.getId(); // sql statement to get name from Product database
    this.dateProduced = new Date();
    this.serialNumber = generateSerialNum(product);
    this.productionNumber = ++prodNumCount;
  }

  ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.prodNumCount = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  /*---------------------------------------------------
    Setters and Getters (accessors and mutators)
  ---------------------------------------------------*/
  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public int getProductID() {
    return productID;
  }

  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public Date getProdDate() {
    return new Date(dateProduced.getTime());
  }

  /*--------------------------------------------------
    This is an SQL statement to get the name from the
    database using the product ID
   ---------------------------------------------------*/
  public String getProductionName() {
    String theName = "";

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

      String sql = "SELECT * FROM PRODUCT WHERE ID = " + "'" + productID + "'";

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        theName = rs.getString("NAME");
      }


      //theID = rs.getInt(1);
      //System.out.println("where am i: " + theID);

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return theName;
  }

  /*---------------------------------------------------
    generateSerialNum
    generates a serial number using the first three
    letters of the manufacturer + the type value +
    a number incremented based on the type for
    every object created.
  ---------------------------------------------------*/
  public String generateSerialNum(Product product) {
    int countOfEnum = 0;

    // increments a different variable depending on the enum
    if (product.type == ItemType.AUDIO || product.type == ItemType.AUDIO_MOBILE) { // needs to be four if statements or a switch statement and 2 more counters
      countOfAudio++;
      countOfEnum = countOfAudio; // generates serial number when production record is created.
    } else if (product.type == ItemType.VISUAL || product.type == ItemType.VISUAL_MOBILE) {
      countOfVisual++;
      countOfEnum = countOfVisual;
    }

    this.serialNumber =
        product.getManufacturer().substring(0, 3) + product.getType().getiType() + String
            .format("%05d", countOfEnum);
    return this.serialNumber;
  }

  public String toString() {
    return "Prod. Num: " + productionNumber +
        "  Product Name: " + getProductionName() +
        "  Serial Num: " + serialNumber +
        "  Date: " + dateProduced + "\n";
  }
}
