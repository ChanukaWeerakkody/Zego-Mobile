package lk.ijse.lms.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.lms.util.Navigation;
import lk.ijse.lms.util.Routes;
import java.io.IOException;


public class LogInFormController {
    public AnchorPane pane;
    public Button btnLog;
    public AnchorPane mainPane;
    public Pane subPane;

    public void LogInOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD, mainPane);
    }
}
