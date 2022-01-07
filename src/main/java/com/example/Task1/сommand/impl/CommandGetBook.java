package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.BookDaoImpl;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CommandGetBook implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BookDaoImpl bookService=new BookDaoImpl();
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
        return "mainPage.jsp";
    }
}
