package com.example.Task1.dao.impl;

import com.example.Task1.dao.AuthorDao;
import com.example.Task1.dao.pool.ConnectionPool;
import com.example.Task1.models.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private QueryExecutor executor = QueryExecutor.getInstance();

    private static final String GET_BOOK_ID_BY_AUTHOR = "SELECT book_id FROM AUTHOR_BOOK WHERE author_id=?";
    private static final String GET_AUTHOR_ID_BY_BOOK = "SELECT author_id FROM AUTHOR_BOOK WHERE book_id=?";
    private static final String ADD_NEW_AUTHOR = "INSERT INTO AUTHORS(authorname,path) values (?,?) ON CONFLICT DO UPDATE";
    private static final String GET_AUTHOR_ID_BY_NAME = "SELECT id FROM AUTHORS WHERE authorname=?";
    private static final String GET_AUTHOR_BY_ID = "SELECT authorname,path FROM Authors WHERE id=?";
    private static final String ADD_AUTHOR_ID="INSERT INTO author_book (book_id, author_id) VALUES (?, ?)";


    public Long getAuthorByName(String authorName) {
        Long id = null;
        try {
            ResultSet resultSet = executor.getResultSet(GET_AUTHOR_ID_BY_NAME, authorName);
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Long> findBookIdByAuthor(Long id) {
        List<Long> list = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_BOOK_ID_BY_AUTHOR, id);
            while (resultSet.next()) {
                id = resultSet.getLong("book_id");
                list.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Long> findAuthorIdByBook(Long id) {
        List<Long> list = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_AUTHOR_ID_BY_BOOK, id);
            while (resultSet.next()) {
                id = resultSet.getLong("author_id");
                list.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //что длетаь если изображения не все
    public Long addNewAuthor(String name, String path) {
        Long id = null;
        try {
            executor.executeStatement(ADD_NEW_AUTHOR);
            id = getAuthorByName(name);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return id;
    }



    public Author getAuthorById(Long id) {
        Author author = new Author();
        try {
            ResultSet resultSet = executor.getResultSet(GET_AUTHOR_BY_ID,id);
            while (resultSet.next()) {
                author.setAuthorName(resultSet.getString("authorname"));
                author.setPath(resultSet.getString("path"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return author;

    }

    public void addAuthorId(Long bookId, Long authorId) {
        try{
            executor.executeStatement(ADD_AUTHOR_ID,bookId,authorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Author> addAuthorToList(String[] authors, String[] authorImage) {
        List<Author> list = new ArrayList<>();
        for (int i = 0; i < authors.length; i++) {
            Long authorId = addNewAuthor(authors[i], authorImage[i]);
            list.add(new Author(authorId, authors[i], authorImage[i]));
        }
        return list;
    }


    public void addAuthorToBook(Long bookId, List<Author> authors) {
        for (Author author : authors) {
            addAuthorId(bookId, author.getId());
        }
    }
}

