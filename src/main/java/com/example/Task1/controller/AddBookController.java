package com.example.Task1.controller;

import com.example.Task1.dao.impl.AuthorDaoImpl;
import com.example.Task1.dao.impl.BookCopyDaoImpl;
import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.dao.impl.GenreDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBookController extends HttpServlet {

    BookDaoImpl bookService;
    GenreDaoImpl genreToBookService;
    GenreDaoImpl genreService;
    AuthorDaoImpl authorService;
    BookCopyDaoImpl bookCopyService;

    public AddBookController() {
        bookService = new BookDaoImpl();
        genreToBookService = new GenreDaoImpl();
        genreService = new GenreDaoImpl();
        authorService = new AuthorDaoImpl();
        bookCopyService = new BookCopyDaoImpl();
    }

    @Override
    public void init() {
    }

    @Override
    //@Path("/auth")
    //author добавить
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       /* request.setCharacterEncoding("UTF-8");
        Book book = new Book();
        List<Genre> genreList;
        List<Author> authorList;
        String path = request.getParameter("image");
        String[] genres = request.getParameterValues("genre");
        String[] authors = request.getParameterValues("author");
        String[] authorImage = request.getParameterValues("authorImage");
        book.setName(request.getParameter("russianName"));
        book.setOriginalName(request.getParameter("originalName"));
        book.setCount(Integer.valueOf(request.getParameter("count")));
        book.setYear(Integer.valueOf(request.getParameter("year")));
        book.setCountPages(Integer.valueOf(request.getParameter("countPages")));
        Double price = Double.valueOf(request.getParameter("price"));
        Double priceForDay = Double.valueOf(request.getParameter("priceForDay"));
        genreList=genreService.addNewGenres( genres);
        book.setGenres(genreList);
        authorList=authorService.addAuthorToList( authors, authorImage);
        Long bookId = bookService.addNewBook(book, path);
        genreService.addGenreToBook(bookId,genreList);
        authorService.addAuthorToBook(bookId,authorList);

        bookCopyService.addNewCopy(bookId, price, priceForDay);*/

    }

    @Override
    public void destroy() {
    }
}
