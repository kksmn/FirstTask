package com.example.Task1.dao.impl;

import com.example.Task1.dao.BookDao;
import com.example.Task1.dao.pool.ConnectionPool;
import com.example.Task1.models.*;

import java.sql.*;
import java.util.*;

public class BookDaoImpl implements BookDao {

    private QueryExecutor executor = QueryExecutor.getInstance();

    private static final String SELECT_FROM_BOOK = "SELECT * FROM BOOK WHERE russianname=?";
    private static final String COUNT_BOOK_ROWS = "SELECT COUNT(*) AS rowcount FROM BOOK";
    private static final String GET_BOOK_BY_ID = "SELECT russianname FROM BOOK WHERE id=?";
    private static final String UPDATE_USER = "UPDATE users SET password = ?, email = ? WHERE id = ?";
    GenreDaoImpl genreService;
    AuthorDaoImpl authorService;
    ReaderDaoImpl readerService;
    BookCopyDaoImpl bookCopyService;
    OrderDaoImpl orderService;

    public BookDaoImpl() {

        genreService = new GenreDaoImpl();
        authorService = new AuthorDaoImpl();
        readerService = new ReaderDaoImpl();
        bookCopyService = new BookCopyDaoImpl();
        orderService = new OrderDaoImpl();
    }

    public Map<Long, Book> findBookByName(String bookName) {
        Map<Long, Book> bookMap = new HashMap<>();
        try {
            ResultSet resultSet = executor.getResultSet(SELECT_FROM_BOOK);
            while (resultSet.next()) {
                Book book = new Book();
                List<Author> authors = new ArrayList<>();
                book.setId(resultSet.getLong("id"));
                book.setBookPictureId(resultSet.getLong("bookpicture_id"));
                book.setOriginalName(resultSet.getString("russianname"));
                book.setYear(resultSet.getInt("year"));
                book.setCount(resultSet.getInt("count"));
                for (Long id : authorService.findAuthorIdByBook(book.getId())) {
                    authors.add(authorService.getAuthorById(id));
                }
                bookMap.put(book.getId(), book);
                book.setAuthors(authors);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookMap;
    }

    public String getBookById(Long id) {
        String bookName = null;
        try {
            ResultSet resultSet = executor.getResultSet(GET_BOOK_BY_ID, id);
            while (resultSet.next()) {
                bookName = resultSet.getString("russianname");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookName;
    }

    public Integer findAuthorIdByName(String authorName) {//MANYAUTHORS
        String sql = "SELECT id FROM AUTHORS WHERE authorname=?";
        Integer id = null;

        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, authorName);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Long getBookIdByAuthorAndName(String bookName, String authorName) {//MANYAUTHORS
        String sql = "SELECT id FROM AUTHORS WHERE authorname=?";
        String sqlQuery = "SELECT id FROM BOOK WHERE russianname=?";
        String authorsQuery = "SELECT book_id FROM AUTHOR_BOOK WHERE author_id=? ";

        List<Long> bookList = new ArrayList<>();
        List<Long> resultList = new ArrayList<>();
        Long authorId = null;
        try {

            ResultSet resultSet = executor.getResultSet(sqlQuery, bookName);
            while (resultSet.next()) {
                bookList.add(resultSet.getLong("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet resultSet = executor.getResultSet(sql, authorName);
            while (resultSet.next()) {
                authorId = resultSet.getLong("id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet resultSet = executor.getResultSet(authorsQuery, authorId);
            while (resultSet.next()) {
                resultList.add(resultSet.getLong("book_id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Long id : resultList) {
            for (Long bookId : bookList) {
                if (id == bookId)
                    return id;
            }


        }
        return null;
    }


    public Long getCopyId(String name) {
        String sql = "SELECT id FROM BookCopy WHERE book_id=?";
        Long bookId = null;
        Long copyId = null;
        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(sql)) {
           /* bookId=findBookByName(name);
             stmt.setInt(1, bookId);
            ResultSet resultSet=stmt.executeQuery();
            while(resultSet.next()){
                copyId=resultSet.getInt("id");
            }
*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return copyId;

    }

    public Calendar getDateForReading() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        return cal;
    }

    public Calendar getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal;
    }

    public long getDifferenceBetweenDate(Calendar lastDate) {
        Calendar currentDate = getCurrentDate();
        lastDate.set(Calendar.MILLISECOND, 0);
        lastDate.set(Calendar.MINUTE, 0);
        lastDate.set(Calendar.HOUR, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.HOUR, 0);

        long remainingDays = Math.round((float) (lastDate.getTimeInMillis() - currentDate.getTimeInMillis()) / (24 * 60 * 60 * 1000));

        return remainingDays;
    }

    public Double getBookPrice(BookCopy copy) {
        Calendar calendar = getDateForReading();
        int countOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return countOfDays * copy.getPriceForDay();
    }

    //дату установить
    public Long addNewBook(Book book, String path) {
        // int affectedRows = statement.executeUpdate();
        //
        //        if (affectedRows == 0) {
        //            throw new SQLException("Creating user failed, no rows affected.");
        //        }
        String sql = "INSERT INTO BOOK (russianname, originalname,count,bookpicture_id ,year,countpages ) VALUES (?, ?,?,?,?,?) RETURNING id";
        String sqlQuery = "INSERT INTO BOOKPICTURE (bookPicturePath) VALUES (?) RETURNING id";
        Long id = null;
        Long pathId = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
                stmt.setString(1, path);
                stmt.execute();
                ResultSet resultSet = stmt.getResultSet();
                if (resultSet.next()) {
                    pathId = resultSet.getLong(1);
                }
            }
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, book.getName());
                stmt.setString(2, book.getOriginalName());
                stmt.setInt(3, book.getCount());
                stmt.setLong(4, pathId);
                stmt.setInt(5, book.getYear());
                //Date date = new Date();
                //stmt.setDate(7, (java.sql.Date) date);
                stmt.setInt(6, book.getCountPages());
                stmt.execute();
                ResultSet lastBook = stmt.getResultSet();
                if (lastBook.next()) {
                    id = lastBook.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    public Map<Long, Book> getBooks(int start, int total) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Book limit ? OFFSET ?";
        Map<Long, Book> bookMap = new HashMap<>();
        GenreDaoImpl genreService = new GenreDaoImpl();
        try {
            ResultSet resultSet = executor.getResultSet(sql,start,total);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(1));
                book.setName(resultSet.getString(2));
                book.setOriginalName(resultSet.getString(3));
                book.setCount(resultSet.getInt(4));
                book.setBookPictureId(resultSet.getLong(5));
                book.setRegistrationDate(resultSet.getDate(6));
                book.setCountPages(resultSet.getInt(7));
                book.setYear(resultSet.getInt(8));
                List<Long> genresId = genreService.getGenreIdByBookId(book.getId());
                List<Genre> genres = new ArrayList<>();
                for (Long id : genresId) {
                    genres.add(genreService.getGenreById(id));
                }

                bookMap.put(book.getId(), book);
                book.setGenres(genres);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookMap;
    }

    //лучше списком или по одному
    public Integer getBookByCopyId(Integer copyId) {
        Integer id = null;
        String sql = "SELECT bookid FROM BookCopy WHERE book_id=?";
        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, copyId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                copyId = resultSet.getInt("id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return copyId;

    }

    //скидка
    public void getBook(String bookName, String authorName, Reader reader) throws SQLException, ClassNotFoundException {
      /*  Long readerId = null;
        readerId = readerService.findReader(reader.getFirstName(), reader.getLastName(), reader.getEmail());
        List<Long> copyList = new ArrayList<>();
        List<Long> bookList = new ArrayList<>();
        int countOrders = 0;
        BookCopy copy = new BookCopy();

        //поиск читателя
        if (readerId == null) {
            readerService.addReader(reader);
            readerId = readerService.findReader(reader.getFirstName(), reader.getLastName(), reader.getEmail());
        }
        //нет долга
        //добавить isAvailable
        if (!orderService.getReaderDebt(reader)) {
            Long bookId = getBookIdByAuthorAndName(bookName, authorName);
            copy = bookCopyService.getCopyOfAvailableBook(bookId);
            if (copy.getId() != null) {
                bookCopyService.makeBookNotAvailable(copy.getId());
                countOrders = orderService.countOrders(readerId);
                if (countOrders <= 5) {
                    Order order = new Order();
                    order.setReaderId(reader.getId());
                    order.setCopy_id(copy.getId());
                    order.setDate(getDateForReading());
                    if (countOrders > 2) {
                        order.setPrice(getBookPrice(copy) * 0.9);
                    } else if (countOrders > 4) {
                        order.setPrice(getBookPrice(copy) * 0.85);

                    } else {
                        order.setPrice(getBookPrice(copy));
                    }
                    orderService.addNewOrder(order);
                }

            }


        }*/
    }

    public int getNoOfRecords() {
        int rowcount = 0;
        try {
            ResultSet resultSet = executor.getResultSet(COUNT_BOOK_ROWS);
            while (resultSet.next()) {
                 rowcount = (int) resultSet.getLong("rowcount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowcount;
    }
}



