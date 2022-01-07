package com.example.Task1.dao;

import com.example.Task1.models.Genre;

import java.util.List;

public interface GenreDao {
    Long getGenreByName(String name);
    Genre getGenreById(Long id);
    List<Long> getGenreIdByBookId(Long bookId);
    List<Genre> addNewGenres(String[] genres);
    void addGenreToBook(Long bookId,List<Genre> genres);
    void addNewGenre(Long book_id, Long genre_id);
}
