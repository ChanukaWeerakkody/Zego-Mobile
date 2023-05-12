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
import java.sql.SQLException;

public class UpdateBookFormController {
    public AnchorPane pneContainer;
    public Button btnUpdate;
    public Button btnDelete;
    public JFXTextField txtIsbn;
    public JFXTextField txtTitle;
    public JFXTextField txtAuthor;
    public JFXTextField txtQty;

    private void fillData(BookTM bookTM) {
        txtIsbn.setText(bookTM.getIsbn());
        txtTitle.setText(bookTM.getTitle());
        txtAuthor.setText(bookTM.getAuthor());
        txtQty.setText(String.valueOf(bookTM.getQty()));
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.BOOKS, pneContainer);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
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

        String isbn=txtIsbn.getText();
        String title=txtTitle.getText();
        String author=txtAuthor.getText();
        int qty= Integer.parseInt(txtQty.getText());

        BookTM bookTM=new BookTM(isbn,title,author,qty);

        boolean isAdded= ManageBookModel.updateBook(bookTM);
        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Book has been successfully updated!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Failed to update the Book details,try again!").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtIsbn.getText();
        try {
            boolean delete =  ManageBookModel.delete(id);
            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Book Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Deleted Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtIsbnOnAction(ActionEvent actionEvent) {
        String id = txtIsbn.getText();

        BookTM bookTM = null;
        try {
            bookTM = ManageBookModel.search(id);
            if(bookTM != null) {
                fillData(bookTM);
            } else {
                new Alert(Alert.AlertType.WARNING, "Book Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
