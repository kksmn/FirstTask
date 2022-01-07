package com.example.Task1.controller;

import com.example.Task1.models.Reader;
import com.example.Task1.dao.impl.ReaderDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/addReader")
public class ReaderListController extends HttpServlet {

    public ReaderDaoImpl readerService;
    public ReaderListController(){
        readerService=new ReaderDaoImpl();
    }
    @Override
    public void init(){ }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        Reader reader=new Reader();
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
    }

}
