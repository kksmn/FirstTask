package com.example.Task1.dao;

import com.example.Task1.models.Book;

import java.sql.SQLException;
import java.util.Map;

public interface BookDao {
    Map<Long, Book> findBookByName(String bookName);
    String getBookById(Long id);
    Long getBookIdByAuthorAndName(String bookName, String authorName);
    Long getCopyId(String name);
    Long addNewBook(Book book, String path);
    Map<Long,Book> getBooks(int start,int total) throws SQLException, ClassNotFoundException;
    Integer getBookByCopyId(Integer copyId);





}
