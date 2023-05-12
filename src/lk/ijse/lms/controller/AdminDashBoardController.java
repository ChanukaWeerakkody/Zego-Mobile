package lk.ijse.lms.controller;

import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.lms.util.Navigation;
import lk.ijse.lms.util.Routes;
import java.io.IOException;

public class AdminDashBoardController {
    public AnchorPane mainPane;
    public AnchorPane subPane;
    public ImageView imgLogo;
    public AnchorPane pneContainer;


    public void homeOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.LOG, mainPane);
    }

    public void memberOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MEMBER, pneContainer);
    }

    public void booksOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.BOOKS, pneContainer);
    }

    public void issueOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ISSUEBOOKS, pneContainer);
    }

    public void returnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RETURNBOOKS, pneContainer);
    }
}
