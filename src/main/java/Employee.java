import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {

  StringBuilder sBuild;
  String username = "default";
  String password;
  String email;

  Employee(String name, String username, String password, String email) {
    this.sBuild = new StringBuilder(name);
    this.username = username;
    this.password = password;
    this.email = email;
  }

  Employee(String name, String password) {
    sBuild = new StringBuilder(name);
    this.password = password;

    if (!checkName(name)) {
      this.email = "user@oracleacademy.Test";
    }

    if (!isValidPassword()) {
      this.password = "pw";
    }
  }

  /*------------------------------------------
    setUsername:
    checks if the name has spaces and sets the
    username based on that.
    parameters:
    name - name used to create and set username
   -------------------------------------------*/
  private void setUsername(String name) {
    for (int i = 0; i < name.length(); i++) { // loop through the name looking for spaces
      char checkChar = name.charAt(i);
      if (checkChar == ' ') { // if there is a space do the following
        this.username = (name.charAt(0) + name.substring(i + 1, (name.length()))).toLowerCase();
        break;
      }
    }

  }

  /*------------------------------------------
  setEmail:
  sets the email using a given name
  parameters:
  name - name used to create and set user email
  -------------------------------------------*/
  private void setEmail(String name) {

    String emailName = name.replace(' ', '.');
    emailName = emailName.toLowerCase();
    this.email = emailName + "@oracleacademy.Test";

  }

  /*------------------------------------------
  isValidPassword:
  checks to see if the password has a special,
  uppercase, and lowercase character.
  returns boolean based on if the password is valid
  **valid = true
  -------------------------------------------*/
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
    }

    uppercase = !password.equals(password.toLowerCase());
    lowercase = !password.equals(password.toUpperCase());

    if (lowercase && uppercase && special) {
      return true;
    } else {
      return false;
    }

  }

  /*------------------------------------------
    checkName:
    checks the name for a space and calls
    set username and setEmail if it does have a
    space
    returns a boolean depending on name Validity
    parameters:
    name - the name to be checked and used when
    setting the email and username.
   -------------------------------------------*/
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

  /*------------------------------------------
    reverseString:
    reverses any string passed in.
    returns reversed string
    parameters:
    word - word to be reversed
   -------------------------------------------*/
  public static String reverseString(String word) {
    // Paste the code for your reverseString method here.
    if (word.length() <= 1) {
      return word;
    } else {
      return word.charAt(word.length() - 1) + reverseString(word.substring(0, (word.length() - 1)));
    }
  }

  public String toString() {
    return
        "Name : " + sBuild + "\n" +
        "Username : " + username + "\n" +
        "Email : " + email + "\n" +
        "Initial Password : " + password;
  }

}
