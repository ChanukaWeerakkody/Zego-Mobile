package lk.ijse.lms.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class initPreLoaderController implements Initializable {


    public Label lblLoading;
    public AnchorPane subPane;
    public static Label lblLoadingg;
    public Rectangle rceContainer;
    public Rectangle rceLoarding;
    public Label lblLoard;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoadingg=lblLoading;
        Timeline timeline=new Timeline();
        KeyFrame keyFrame=new KeyFrame(Duration.millis(500),actionEvent ->{
                lblLoard.setText("Initializing Application....");
                rceLoarding.setWidth(rceContainer.getWidth()*0.2);
        });

        KeyFrame keyFrame1=new KeyFrame(Duration.millis(1000),actionEvent ->{
            lblLoard.setText("Loading Internal Resources....");
            rceLoarding.setWidth(rceContainer.getWidth()*0.4);
        });

        KeyFrame keyFrame2=new KeyFrame(Duration.millis(1500),actionEvent ->{
            lblLoard.setText("Loading Images....");
            rceLoarding.setWidth(rceContainer.getWidth()*0.6);
        });

        KeyFrame keyFrame3=new KeyFrame(Duration.millis(2000),actionEvent ->{
            lblLoard.setText("Loading UIs....");
            rceLoarding.setWidth(rceContainer.getWidth()*0.8);
        });

        KeyFrame keyFrame4=new KeyFrame(Duration.millis(2500),actionEvent ->{
            lblLoard.setText("Welcome to LMS v1.0.0");
            rceLoarding.setWidth(rceContainer.getWidth()*1);
        });

        KeyFrame keyFrame5=new KeyFrame(Duration.millis(3000),actionEvent ->{
            Stage stage=new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/lk/ijse/lms/view/LogInForm.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Stage window =(Stage) lblLoading.getScene().getWindow();
                window.hide();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        timeline.getKeyFrames().addAll(keyFrame,keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5);
        timeline.playFromStart();
    }

}
