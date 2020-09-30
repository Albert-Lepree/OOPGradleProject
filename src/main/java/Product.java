public abstract class Product implements Item {

  int id;
  ItemType type;
  String manufacturer;
  String name;

  public Product(){
  }

  public Product(String nameIn, String manufacturerIn, ItemType typeIn) {
    type = typeIn;
    manufacturer = manufacturerIn;
    name = nameIn;
  }

  public int getId() {
    return id;
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

  public String toString() {
    return "Name: " + name + "\n" +
        "Manufacturer: " + manufacturer + "\n" +
        "Type: " + type.getiType();
  }
}
