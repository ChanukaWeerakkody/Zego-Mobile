package lk.ijse.lms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.lms.model.ManageBookModel;
import lk.ijse.lms.to.BookTM;
import lk.ijse.lms.util.Navigation;
import lk.ijse.lms.util.Routes;

import java.io.IOException;

public class AddBookFormController {
    public AnchorPane pneContainer;
    public JFXTextField txtIsbn;
    public JFXTextField txtTitle;
    public JFXTextField txtAuthor;
    public JFXTextField txtQty;
    public Button btnRegister;
    public Button btnClear;

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.BOOKS, pneContainer);
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        if (txtIsbn.getText().isEmpty() || !txtIsbn.getText().matches("\\d{13}")) {
            new Alert(Alert.AlertType.ERROR,"ISBN invalid or empty").show();
            txtIsbn.selectAll();
            txtIsbn.requestFocus();
            return;
        }
        else if (txtTitle.getText().isEmpty() || !txtTitle.getText().matches("^[A-Za-z0-9 ]+$")){
            new Alert(Alert.AlertType.ERROR,"Name cannot be empty").show();
            txtTitle.selectAll();
            txtTitle.requestFocus();
            return;
        }else if (txtAuthor.getText().isEmpty() || !txtAuthor.getText().matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR,"Address cannot be empty").show();
            txtAuthor.selectAll();
            txtAuthor.requestFocus();
            return;
        }else if (txtQty.getText().isEmpty() || !txtQty.getText().matches("^\\d+$")){
            new Alert(Alert.AlertType.ERROR,"Contact cannot be empty").show();
            txtQty.selectAll();
            txtQty.requestFocus();
            return;
        }
        else if (ManageBookModel.existBookByIsbn(txtIsbn.getText())) {
            new Alert(Alert.AlertType.ERROR,"Member Already Exists").show();
            txtIsbn.selectAll();
            txtIsbn.requestFocus();
            return;
        }

        String isbn=txtIsbn.getText();
        String title=txtTitle.getText();
        String author=txtAuthor.getText();
        int qty= Integer.parseInt(txtQty.getText());

        BookTM bookTM=new BookTM(isbn,title,author,qty);

        boolean isAdded= ManageBookModel.addBook(bookTM);
        if(isAdded){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Registered !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Failed to Save the book !").show();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField(){
        txtIsbn.clear();
        txtAuthor.clear();
        txtTitle.clear();
        txtQty.clear();
    }
}
