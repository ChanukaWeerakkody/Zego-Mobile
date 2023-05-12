package lk.ijse.lms.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();
        switch (route){
            case LOG:
                window.setTitle("Log iN");
                initUI("LogInForm.fxml");
                break;
            case ADMINDASHBOARD:
                window.setTitle("DashBoard");
                initUI("AdminDashBoard.fxml");
                break;
            case MEMBER:
                window.setTitle("New");
                initUI("ManageMember.fxml");
                break;
            case ADDMEMBER:
                window.setTitle("New Member");
                initUI("AddMemberForm.fxml");
                break;
            case UPDATEMEMBER:
                window.setTitle("Update Member");
                initUI("UpdateMemberForm.fxml");
                break;

            case ISSUELIST:
                window.setTitle("Show List");
                initUI("ShowIssuedListForm.fxml");
                break;
            case BOOKS:
                window.setTitle("Manage Books");
                initUI("ManageBooks.fxml");
                break;
            case ADDBOOKS:
                window.setTitle("Add Books");
                initUI("AddBookForm.fxml");
                break;
            case UPDATEBOOKS:
                window.setTitle("Update Books");
                initUI("UpdateBookForm.fxml");
                break;
            case ISSUEBOOKS:
                window.setTitle("Issue Books");
                initUI("ManageIssueBooks.fxml");
                break;
            case RETURNBOOKS:
                window.setTitle("Return Books");
                initUI("ManageReturnBooks.fxml");
                break;

            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/lms/view/" + location)));
    }
}
