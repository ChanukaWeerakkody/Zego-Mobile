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
import lk.ijse.lms.model.ManageBookModel;
import lk.ijse.lms.to.BookTM;
import lk.ijse.lms.util.Navigation;
import lk.ijse.lms.util.Routes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ManageBookController {
    public AnchorPane pneContainer;
    public TextField txtSearch;
    public JFXButton btnAddBook;
    public JFXButton btnUpdate;
    public TableView tblBooks;
    public TableColumn colIsbn;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colQty;

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<BookTM> books = ManageBookModel.getList();
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblBooks.setItems(books);

        txtSearch.textProperty().addListener((observableValue, pre, curr) ->{
            if (!Objects.equals(pre, curr)){
                tblBooks.getItems().clear();
                tblBooks.setItems(FXCollections.observableArrayList(ManageBookModel.searchBooks(curr)));
            }

        } );

        tblBooks.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                btnUpdate.setDisable(false);
            }

        });
    }

    public void btnAddBookOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDBOOKS, pneContainer);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATEBOOKS, pneContainer);
    }
}
