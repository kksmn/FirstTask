package com.example.Task1.controller;

import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.models.Book;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet("/mainPage")
public class MainServlet extends HttpServlet {

    public BookDaoImpl bookService;
    public MainServlet(){
        bookService=new BookDaoImpl();
    }

    @Override
    public void init(){ }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
           int page = 1;
           int n;
           int recordsPerPage = 1;
           if(request.getParameter("page") != null)
               page = Integer.parseInt(request.getParameter("page"));
           if(page==3)
               n=2;
               Map<Long, Book> list = bookService.getBooks(recordsPerPage,
                   (page-1)*recordsPerPage);
           int noOfRecords = bookService.getNoOfRecords();
           int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
           request.setAttribute("noOfPages", noOfPages);
           request.setAttribute("currentPage", page);

            request.setAttribute("list", list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/mainPage.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
    }
}