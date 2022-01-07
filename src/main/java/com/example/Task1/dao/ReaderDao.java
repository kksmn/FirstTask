package com.example.Task1.dao;

import com.example.Task1.models.Reader;

import java.sql.SQLException;
import java.util.List;

public interface ReaderDao {
    void addReader(Reader reader) throws SQLException, ClassNotFoundException;
    List<Reader> findReader(String email) throws SQLException, ClassNotFoundException;
    List<Reader> getReaders(int start, int total) throws SQLException, ClassNotFoundException;

}
