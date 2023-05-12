package lk.ijse.lms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.lms.model.ManageMemberModel;
import lk.ijse.lms.to.MemberTM;
import lk.ijse.lms.util.Navigation;
import lk.ijse.lms.util.Routes;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateMemberFormController {
    public AnchorPane pneContainer;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public Button btnUpdate;
    public Button btnDelete;
    public Label lblId;

    public JFXTextField txtID;


    private void fillData(MemberTM memberTM) {
        txtID.setText(memberTM.getId());
        txtName.setText(memberTM.getName());
        txtAddress.setText(memberTM.getAddress());
        txtContact.setText(memberTM.getContact());
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.MEMBER, pneContainer);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (txtName.getText().isEmpty() || !txtName.getText().matches("^[A-Za-z ]+$")){
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
        String id=txtID.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();

        MemberTM memberTM=new MemberTM(id,name,address,contact);

        boolean update=ManageMemberModel.updateMember(memberTM);
        if(update){
            new Alert(Alert.AlertType.INFORMATION,"Member has been successfully updated!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Failed to update the Member,try again!").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        try {
            boolean delete =  ManageMemberModel.delete(id);
            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Member Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Deleted Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();

        MemberTM memberTM = null;
        try {
            memberTM = ManageMemberModel.search(id);
            if(memberTM != null) {
                fillData(memberTM);
            } else {
                new Alert(Alert.AlertType.WARNING, "Member Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
