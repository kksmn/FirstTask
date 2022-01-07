package com.example.Task1.сommand.impl;

import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Reader;
import com.example.Task1.сommand.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class CommandAddReader implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ReaderDaoImpl readerService = new ReaderDaoImpl();
            Reader reader = new Reader();
            reader.setFirstName(request.getParameter("firstName"));
            reader.setLastName(request.getParameter("lastName"));
            reader.setEmail(request.getParameter("email"));
            reader.setPatronymic(request.getParameter("patronymic"));
            reader.setDate(Date.valueOf(request.getParameter("birthDate")));
            reader.setEmail(request.getParameter("email"));
            reader.setAddress(request.getParameter("address"));
            readerService.addReader(reader);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "mainPage.jsp";
    }
}

