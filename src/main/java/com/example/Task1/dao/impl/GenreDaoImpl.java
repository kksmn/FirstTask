package com.example.Task1.dao.impl;

import com.example.Task1.dao.GenreDao;
import com.example.Task1.dao.pool.ConnectionPool;
import com.example.Task1.models.Genre;
import com.example.Task1.dao.impl.GenreDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao {
    private QueryExecutor executor = QueryExecutor.getInstance();

    private static final String GET_GENRE_ID_BY_BOOK = "SELECT genre_id FROM GENRE_BOOK where book_id=?";
    private static final String GET_GENRE_BY_ID = "SELECT * FROM Genres  WHERE id=?";
    private static final String ADD_NEW_AUTHOR = "INSERT INTO AUTHORS(authorname,path) values (?,?) ON CONFLICT DO UPDATE";
    private static final String GET_AUTHOR_ID_BY_NAME = "SELECT id FROM AUTHORS WHERE authorname=?";
    private static final String GET_AUTHOR_BY_ID = "SELECT authorname,path FROM Authors WHERE id=?";
    private static final String ADD_AUTHOR_ID="INSERT INTO author_book (book_id, author_id) VALUES (?, ?)";
    public GenreDaoImpl() {
    }

    public Long getGenreByName(String name) {
        String sql = "SELECT id FROM Genres WHERE genrename=?";
        Long genreId = null;
        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                genreId = resultSet.getLong("id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genreId;

    }

    public Genre getGenreById(Long id) {
        Genre genre = new Genre();
        try {
            ResultSet resultSet = executor.getResultSet(GET_GENRE_BY_ID,id);
            while (resultSet.next()) {
                genre.setId(id);
                genre.setGenreName(resultSet.getString("genrename"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genre;

    }

    public List<Long> getGenreIdByBookId(Long bookId) {
        List<Long> genreId = new ArrayList<>();
        try {
            ResultSet resultSet = executor.getResultSet(GET_GENRE_ID_BY_BOOK,bookId);
            while (resultSet.next()) {
                genreId.add(resultSet.getLong("genre_id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genreId;

    }

    public void addNewGenre(Long book_id, Long genre_id) {
        String sql = "INSERT INTO genre_book (book_id, genre_id) VALUES (?, ?)";
        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setLong(1, book_id);
            stmt.setLong(2, genre_id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Genre> addNewGenres(String[] genres) {
        List<Genre> list = new ArrayList<>();
        for (int i = 0; i < genres.length; i++) {
            Long genre_id = getGenreByName(genres[i]);
            list.add(new Genre(genre_id, genres[i]));
        }
        return list;
    }

    public void addGenreToBook(Long bookId, List<Genre> genres) {
        for (Genre genre : genres) {
            addNewGenre(bookId, genre.getId());
        }
    }
}
