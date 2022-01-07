package com.example.Task1.dao.impl;

import com.example.Task1.dao.BookCopyDao;
import com.example.Task1.dao.impl.QueryExecutor;
import com.example.Task1.dao.pool.ConnectionPool;
import com.example.Task1.models.BookCopy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCopyDaoImpl implements BookCopyDao {
    private QueryExecutor executor = QueryExecutor.getInstance();

    private static final String CHECK_IS_BOOK_AVAILABLE =  "SELECT isAvailable FROM BookCopy WHERE id=?";
    private static final String MAKE_BOOK_NOT_AVAILABLE = "Update BookCopy SET isAvailable=false WHERE id = ?";
    private static final String GET_COPY_OF_AVAILABLE_BOOK = "INSERT INTO AUTHORS(authorname,path) values (?,?) ON CONFLICT DO UPDATE";
    private static final String ADD_NEW_BOOK_COPY = "INSERT INTO BOOKCOPY (bookid,isDamaged,isAvailable,price,priceForDay) VALUES (?,?,?,?,?)";
    private static final String MAKE_BOOK_AVAILABLE = "Update BookCopy SET isAvailable=true WHERE book_id = ?";
    private static final String SET_BOOK_COPY_RAITING="Update BookCopy SET raiting=? WHERE book_id = ?";

    public boolean isBookAvailable(Long id) {
        Boolean isAvailable = false;
        try{
            ResultSet resultSet=executor.getResultSet(CHECK_IS_BOOK_AVAILABLE,id);
            while(resultSet.next()){
                isAvailable=resultSet.getBoolean("isAvailable");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return isAvailable;
    }
    public BookCopy getCopyOfAvailableBook(Long id) {
        BookCopy copy=new BookCopy();
        try{
            ResultSet resultSet=executor.getResultSet(GET_COPY_OF_AVAILABLE_BOOK,id);
            while(resultSet.next()){
                copy.setId(resultSet.getLong("id"));
                copy.setBook_id(resultSet.getLong("bookid"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return copy;
    }
    public void makeBookNotAvailable(Long id){
        try {
            executor.executeStatement(MAKE_BOOK_NOT_AVAILABLE,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewCopy(Long bookId,Double price, Double priceForDay){
        try{
            executor.executeStatement(ADD_NEW_BOOK_COPY,bookId,price,priceForDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void makeBookAvailable(Integer id){
        try{
            executor.executeStatement(MAKE_BOOK_AVAILABLE,id);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   /* public void setRaiting(Long id,Double raiting) {
        String sql = "Update BookCopy SET raiting=? WHERE book_id = ?";
        Integer copyId=null;
        try{
            ResultSet resultSet=executor.getResultSet(SET_BOOK_COPY_RAITING,raiting,id);
            while(resultSet.next()){
                copyId=resultSet.getInt("id");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }*/

    public Long getCopyByBookId(Long id) {
        String sql = "SELECT id FROM BookCopy WHERE bookid=?";
        Long copyId=null;
        try(PreparedStatement stmt= ConnectionPool.getInstance().getConnection().prepareStatement(sql)){
            stmt.setLong(1, id);
            ResultSet resultSet=stmt.executeQuery();
            while(resultSet.next()){
                copyId=resultSet.getLong("id");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return copyId;
    }

}
