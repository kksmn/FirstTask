package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.models.Book;
import com.example.Task1.сommand.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CommandGetBookTable implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BookDaoImpl bookService=new BookDaoImpl();
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
        return "mainPage.jsp";
    }
}
