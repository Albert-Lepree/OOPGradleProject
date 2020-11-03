import java.util.Date;

/*---------------------------------------------------
ProcutionRecord Class:
intended to record things about the prouct such as
serial number, date created, id number, etc.
this class contains constructors and accessors/
mutators for those values.
---------------------------------------------------*/
public class ProductionRecord {

  static int countOfAudio = 0;
  static int countOfVisual = 0;

  private int productionNumber = 0;
  private static int prodNumCount; // I had to make a second prod num count because the static variable was being odd while calling two different types of constructors
  private int productID;
  private String serialNumber;
  private Date dateProduced;
  private int numItems;
  private Product product;

  ProductionRecord(Product product, int numItems) {
    this.numItems = numItems; // never used?
    this.product = product;
    this.dateProduced = new Date();
    this.serialNumber = generateSerialNum();
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

  /*---------------------------------------------------
    generateSerialNum
    generates a serial number using the first three
    letters of the manufacturer + the type value +
    a number incremented based on the type for
    every object created.
  ---------------------------------------------------*/
  public String generateSerialNum() {
    int countOfEnum = 0;

    // increments a different variable depending on the enum
    if (product.type == ItemType.AUDIO || product.type == ItemType.AUDIO_MOBILE) {
      countOfAudio++;
      countOfEnum = countOfAudio; // generates serial number when production record is created.
    } else if (product.type == ItemType.VISUAL || product.type == ItemType.VISUAL_MOBILE) {
      countOfVisual++;
      countOfEnum = countOfVisual;
    }

    this.serialNumber =
        product.getManufacturer().substring(0, 3) + product.getType().getiType() + String
            .format("%06d", countOfEnum);
    return this.serialNumber;
  }

  public String toString() {
    return "Prod. Num: " + productionNumber +
        "  Product ID: " + productID +
        "  Serial Num: " + serialNumber +
        "  Date: " + dateProduced + "\n";
  }
}
