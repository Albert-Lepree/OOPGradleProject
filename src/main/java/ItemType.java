public enum ItemType {

  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  private String iType;

  ItemType(String iType) {
    this.iType = iType;
  }

  public String getiType(){
    return iType;
  }

}
