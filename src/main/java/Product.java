import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Product implements Item {

  int id;
  ItemType type;
  String manufacturer;
  String name;

  /*---------------------------------------------------
  Product:
  Default Constructor for Product
  ---------------------------------------------------*/
  public Product() {
  }

  /*---------------------------------------------------
  Product:
  Overload constructor for Product takes enum ItemType
  as a parameter.
  ---------------------------------------------------*/
  public Product(String nameIn, String manufacturerIn, ItemType typeIn) {
    type = typeIn;
    manufacturer = manufacturerIn;
    name = nameIn;
    this.id = getId();
  }

  /*---------------------------------------------------
  Setters & Getters:
  ---------------------------------------------------*/

  /*---------------------------------------------------------
  getID:
   this is an SQL statement to get the product ID using the name
   returns the productID
   -----------------------------------------------------------*/
  public int getId() {
    int theID = 0;

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

      String sql = "SELECT * FROM PRODUCT WHERE NAME = " + "'" + name + "'";

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        theID = rs.getInt("ID");
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

    return theID;
  }

  public String getName() {
    return name;
  }

  public void setName(String nameIn) {
    name = nameIn;
  }

  public void setManufacturer(String manufacturerIn) {
    manufacturer = manufacturerIn;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public ItemType getType() {
    return type;
  }

  /*---------------------------------------------------
    toString:
    Prints the fields above to the console
    note: Unsure if the type should be the enum name or
    the enum values.  ".getiType()"
    returns a formatted string
    ---------------------------------------------------*/
  public String toString() {
    return "Name: " + name + "\n" +
        "Manufacturer: " + manufacturer + "\n" +
        "Type: " + type + "\n";

  }
}
