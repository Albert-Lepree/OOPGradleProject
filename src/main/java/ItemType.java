public enum ItemType {

  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  private String iType;

  ItemType(String iType) {
    this.iType = iType;
  }

  /*---------------------------------------------------
  getiType:
  Returns the value of the string iType, many methods
  use the enum ItemType as a parameter
  ---------------------------------------------------*/
  public String getiType(){
    return iType;
  }

}
