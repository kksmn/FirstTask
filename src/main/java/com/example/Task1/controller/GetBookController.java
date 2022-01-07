package com.example.Task1.controller;

import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Reader;
import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.service.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/getBook")
public class GetBookController extends HttpServlet {

    public BookDaoImpl bookService;
    public LibraryService libraryService;
    public ReaderDaoImpl readerService;

    public GetBookController() {
        bookService = new BookDaoImpl();
        libraryService = new LibraryService();
        readerService=new ReaderDaoImpl();
    }

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int page = 1;
            int n;
            int recordsPerPage = 1;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            List<Reader> list = readerService.getReaders(recordsPerPage,
                    (page-1)*recordsPerPage);
            int noOfRecords = readerService.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            request.setAttribute("list", list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/getBook.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Reader reader = new Reader();
            reader.setFirstName(request.getParameter("firstName"));
            reader.setLastName(request.getParameter("lastName"));
            reader.setEmail(request.getParameter("email"));
            String bookName = request.getParameter("russianname");
            String authorName = request.getParameter("authorName");
            bookService.getBook(bookName, authorName, reader);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
