package lk.ijse.lms.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.lms.db.DBConnection;
import lk.ijse.lms.to.BookTM;
import lk.ijse.lms.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageBookModel {
    public static  List<BookTM> searchBooks(String searchText) {

        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Book WHERE isbn LIKE ? OR  title LIKE ? OR author LIKE ? ");
            searchText="%"+searchText+"%";
            stm.setString(1,searchText);
            stm.setString(2,searchText);
            stm.setString(3,searchText);
            ResultSet rst = stm.executeQuery();
            List<BookTM> bookList=new ArrayList<>();
            while (rst.next()){
                BookTM book = new BookTM(rst.getString("isbn"), rst.getString("title"), rst.getString("author"), rst.getInt("qty"));
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static  List<BookTM> getAllBooks() {
        try {
            Connection connection  = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Book");
            ResultSet rst = stm.executeQuery();
            List<BookTM> bookList =new ArrayList<>();
            while (rst.next()){
                BookTM book = new BookTM(rst.getString("isbn"), rst.getString("title"), rst.getString("author"), rst.getInt("qty"));
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException |ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean existBookByIsbn(String isbn) {
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Book WHERE isbn=?");
            stm.setString(1,isbn);
            ResultSet rst = stm.executeQuery();
            return rst.next();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addBook(BookTM book) {
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Book (isbn, title, author,qty) VALUES (?,?,?,?)");
            stm.setString(1,book.getIsbn());
            stm.setString(2,book.getTitle());
            stm.setString(3,book.getAuthor());
            stm.setInt(4,book.getQty());
            return stm.executeUpdate()==1;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean updateBook(BookTM updatedBook) {
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("UPDATE Book SET title=? ,author=? ,qty=? WHERE isbn=?");
            stm.setString(1,updatedBook.getTitle());
            stm.setString(2,updatedBook.getAuthor());
            stm.setInt(3,updatedBook.getQty());
            stm.setString(4,updatedBook.getIsbn());

            return stm.executeUpdate()==1;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteBookByIsbn(String isbn) {
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("DELETE  FROM Book WHERE isbn=?");
            stm.setString(1,isbn);
            return stm.executeUpdate()==1;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<BookTM> getList() throws SQLException, ClassNotFoundException {
        ObservableList<BookTM> bookTMS = FXCollections.observableArrayList();
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Book");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            bookTMS.add(new BookTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return bookTMS;
    }

    public static BookTM search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Book WHERE isbn = ?", id);

        if(result.next()) {
            return new BookTM(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)
            );
        }
        return null;
    }
    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        return  DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Book where isbn='"+id+"'")>0;
    }
}
