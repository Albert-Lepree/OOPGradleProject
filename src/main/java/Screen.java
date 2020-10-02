/*---------------------------------------------------
Screen Class:
Adds values as well as accessors/mutators that are
needed to get/set these values.
---------------------------------------------------*/

public class Screen implements ScreenSpec {

  String resolution;
  int refreshRate;
  int responseTime;

  Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  public String getResolution() {
    return resolution;
  }

  public int getRefreshRate() {
    return refreshRate;
  }

  public int getResponseTime() {
    return responseTime;
  }

  public String toString() {
    return "Screen:" + "\n" +
        "Resolution: " + resolution + "\n" +
        "Refresh rate: " + refreshRate + "\n" +
        "Response time: " + responseTime;
  }

}
