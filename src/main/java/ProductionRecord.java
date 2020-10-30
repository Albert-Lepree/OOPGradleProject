import java.util.Date;
import java.util.Random;

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

  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;
  private int numItems;
  private Product product;

  ProductionRecord(Product product, int numItems) {
    this.numItems = numItems;
    this.product = product;
    this.dateProduced = new Date();


    if(product.type == ItemType.AUDIO || product.type == ItemType.AUDIO_MOBILE) {
      countOfAudio++;
      generateSerialNum(countOfAudio); // generates serial number when production record is created.
    } else if(product.type == ItemType.VISUAL || product.type == ItemType.VISUAL_MOBILE) {
      countOfVisual++;
      generateSerialNum(countOfVisual);
    }
  }

  ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

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
    return dateProduced;
  }

  public String generateSerialNum(int countOfEnum) {
    Random rand = new Random();
    this.serialNumber = product.getManufacturer().substring(0,3) + product.getType().getiType() + String.format("%06d", countOfEnum)  ;
    return  this.serialNumber;
  }

  public String toString() {
    return "Prod. Num: " + productionNumber +
            "  Product ID: " + productID +
            "  Serial Num: " + serialNumber +
            "  Date: " + dateProduced;
  }
}
