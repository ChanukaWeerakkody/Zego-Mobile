package lk.ijse.lms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.lms.model.ManageMemberModel;
import lk.ijse.lms.to.MemberTM;
import lk.ijse.lms.util.Navigation;
import lk.ijse.lms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class AddMemberFormController {

    public AnchorPane pneContainer;
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public Button btnRegister;
    public Button btnClear;

    public TableView<MemberTM> tblMembers;

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.MEMBER, pneContainer);
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtID.getText().isEmpty() || !txtID.getText().matches("M\\d{3}")) {
            new Alert(Alert.AlertType.ERROR,"Member ID empty or invalid").show();
            txtID.selectAll();
            txtID.requestFocus();
            return;
        }
        else if (txtName.getText().isEmpty() || !txtName.getText().matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR,"Name cannot be empty").show();
            txtName.selectAll();
            txtName.requestFocus();
            return;
        }else if (txtAddress.getText().isEmpty() || !txtAddress.getText().matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR,"Address cannot be empty").show();
            txtAddress.selectAll();
            txtAddress.requestFocus();
            return;
        }else if (txtContact.getText().isEmpty() || !txtContact.getText().matches("^\\d{3}-\\d{7}$")){
            new Alert(Alert.AlertType.ERROR,"Contact cannot be empty").show();
            txtContact.selectAll();
            txtContact.requestFocus();
            return;
        }

        else if (ManageMemberModel.existMemberById(txtID.getText())) {
            new Alert(Alert.AlertType.ERROR,"Member Already Exists").show();
            txtID.selectAll();
            txtID.requestFocus();
            return;
        }

            String id=txtID.getText();
            String name=txtName.getText();
            String address=txtAddress.getText();
            String contact=txtContact.getText();

            MemberTM memberTM=new MemberTM(id,name,address,contact);

            boolean isAdded=ManageMemberModel.addMember(memberTM);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Registered !").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to Save the member !").show();
            }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField(){
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }
}
