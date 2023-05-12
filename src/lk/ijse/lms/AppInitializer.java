package lk.ijse.lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class AppInitializer extends Application {

    public static Stage primaryStage=null;
    public static Scene primaryScene=null;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = this.getClass().getResource("/lk/ijse/lms/view/InitPreLoader.fxml");
        Parent load = FXMLLoader.load(resource);
        primaryStage.setScene(new Scene(load));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
