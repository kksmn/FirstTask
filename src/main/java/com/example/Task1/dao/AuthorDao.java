package com.example.Task1.dao;

import com.example.Task1.models.Author;

import java.util.List;

public interface AuthorDao {
    List<Long> findBookIdByAuthor(Long id);
    List<Long> findAuthorIdByBook(Long id);
    Long addNewAuthor(String name,String path);
    Long getAuthorByName(String name);
    Author getAuthorById(Long id);
    void addAuthorId(Long bookId, Long authorId);
    List<Author> addAuthorToList(String[] authors, String[] authorImage);
    void addAuthorToBook(Long bookId,List<Author> authors);

}
