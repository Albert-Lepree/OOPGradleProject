/*---------------------------------------------------
MoviePlayer Class:
Similar to the audio player class in the way that
products of item type visual would have different
values than that of audio player. I.E. monitorType
---------------------------------------------------*/

public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;

  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  public void play() {
    System.out.println("Playing movie");
  }

  public void stop() {
    System.out.println("Stopping movie");
  }

  public void previous() {
    System.out.println("Previous movie");
  }

  public void next() {
    System.out.println("Next movie");
  }

  public String toString() {
    return super.toString() + screen.toString() + "\n" +
        "Monitor Type: " + monitorType;
  }

}
