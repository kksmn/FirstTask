package com.example.Task1.dao;

import com.example.Task1.models.BookCopy;

public interface BookCopyDao {
    boolean isBookAvailable(Long id);
    BookCopy getCopyOfAvailableBook(Long id);
    void makeBookNotAvailable(Long id);
    void addNewCopy(Long bookId,Double price, Double priceForDay);
    void makeBookAvailable(Integer id);

}
