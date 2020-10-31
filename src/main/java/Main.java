/*---------------------------------------------------
  Albert Finn Lepree
  Note:
  above every method is a comment block to give a
  better idea of what is going on.
---------------------------------------------------*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Scene scene = new Scene(root, 597, 400);

    scene.getStylesheets().add("Colors.css");
    primaryStage.setTitle("Product Database");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
