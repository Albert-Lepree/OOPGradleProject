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
  Overload contstructor for Product takes enum ItemType
  as a parameter.
  ---------------------------------------------------*/
  public Product(String nameIn, String manufacturerIn, ItemType typeIn) {
    type = typeIn;
    manufacturer = manufacturerIn;
    name = nameIn;
  }

  /*---------------------------------------------------
  Setters & Getter:
  setters and getters for the fields above
  ---------------------------------------------------*/
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

  public ItemType getType() {
    return type;
  }

  /*---------------------------------------------------
    toString:
    Prints the fields above to the console
    note: Unsure if the type should be the enum name or
    the enum values.  ".getiType()"
    ---------------------------------------------------*/
  public String toString() {
    return "Name: " + name + "\n" +
        "Manufacturer: " + manufacturer + "\n" +
        "Type: " + type + "\n";

  }
}
