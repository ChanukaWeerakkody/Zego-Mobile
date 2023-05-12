package lk.ijse.lms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.lms.model.ManageMemberModel;
import lk.ijse.lms.to.MemberTM;
import lk.ijse.lms.util.Navigation;
import lk.ijse.lms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ManageMemberController {
    public AnchorPane pneContainer;

    public TableView tblMembers;
    public TextField txtSearchMember;
    public JFXButton btnAddMember;
    public JFXButton btnUpdateDelete;
    public JFXButton btnIssueList;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<MemberTM> customers = ManageMemberModel.getList();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblMembers.setItems(customers);

        txtSearchMember.textProperty().addListener((observableValue, pre, curr) ->{
            if (!Objects.equals(pre, curr)){
                tblMembers.getItems().clear();
                tblMembers.setItems(FXCollections.observableArrayList(ManageMemberModel.searchMembers(curr)));
            }

        } );

        tblMembers.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                btnUpdateDelete.setDisable(false);
                btnIssueList.setDisable(false);
            }

        });
    }

    public void btnAddMemberOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDMEMBER, pneContainer);
    }

    public void btnUpdateDeleteOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATEMEMBER, pneContainer);
    }

    public void btnIssuedListOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ISSUELIST, pneContainer);
    }
}
