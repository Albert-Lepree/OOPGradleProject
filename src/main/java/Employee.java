import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {

  StringBuilder sBuild;
  String username = "default";
  String password;
  String email;

  Employee(String name, String password) {
    sBuild = new StringBuilder(name);
    this.password = password;

    if (checkName(name) == false) {
      this.email = "user@oracleacademy.Test";
    }

    if (isValidPassword() == false) {
      this.password = "pw";
    }
  }

  private void setUsername(String name) {
    for (int i = 0; i < name.length(); i++) { // loop through the name looking for spaces
      char checkChar = name.charAt(i);
      if (checkChar == ' ') { // if there is a space do the following
        this.username = (name.charAt(0) + name.substring(i + 1, (name.length()))).toLowerCase();
        break;
      }
    }

  }

  private void setEmail(String name) {

    String emailName = name.replace(' ', '.');
    emailName = emailName.toLowerCase();
    this.email = emailName + "@oracleacademy.Test";

  }

  private boolean isValidPassword() {
    boolean lowercase = false;
    boolean uppercase = false;
    boolean special = false;

    String inputString = this.password;
    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
    Matcher matcher = pattern.matcher(inputString);
    boolean isStringContainsSpecialCharacter = matcher.find();
    if (isStringContainsSpecialCharacter) {
      special = true;
    } else {
      special = false;
    }

    uppercase = !password.equals(password.toLowerCase());
    lowercase = !password.equals(password.toUpperCase());

    if (lowercase == true && uppercase == true && special == true) {
      return true;
    } else {
      return false;
    }

  }

  private boolean checkName(String name) {
    for (int i = 0; i < name.length(); i++) { // loop through the name looking for spaces
      char checkChar = name.charAt(i);
      if (checkChar == ' ') { // if there is a space do the following
        setUsername(name);
        setEmail(name);
        return true;
      }

    }

    return false;

  } // end checkName

  public String toString() {
    return "Employee Details\n" +
        "Name : " + sBuild + "\n" +
        "Username : " + username + "\n" +
        "Email : " + email + "\n" +
        "Initial Password : " + password;
  }

}
