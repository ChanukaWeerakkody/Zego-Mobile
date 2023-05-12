package lk.ijse.lms.controller;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.lms.model.ManageMemberModel;
import lk.ijse.lms.to.IssueTM;
import lk.ijse.lms.to.MemberTM;
import lk.ijse.lms.util.Navigation;
import lk.ijse.lms.util.Routes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowIssuedListFormController {
    public MemberTM selectedMember;

    public ManageMemberController manageMemberController;
    public AnchorPane pneContainer;
    public Label lblId;
    public Label lblName;
    public Label lblDate;
    public TableView<IssueTM> tblIssueList;
    public TableColumn colId;
    public TableColumn colDate;
    public TableColumn colStatus;

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.MEMBER, pneContainer);
    }

    public void init(MemberTM selectedItem) {
        this.selectedMember=selectedItem;
        initFields(selectedMember);
        tblIssueList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("issueId"));
        tblIssueList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblIssueList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("status"));

        tblIssueList.getItems().addAll(FXCollections.observableArrayList(ManageMemberModel.findAllIssuesById(selectedMember.getId())));
    }


    private void initFields(MemberTM selectedMember) {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        lblId.setText(selectedMember.getId());
        lblName.setText(selectedMember.getName());
    }
}
