package com.example.Task1.controller;

import com.example.Task1.dao.impl.ReaderDaoImpl;
import com.example.Task1.models.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/getReader")
public class GetReaderController extends HttpServlet {
    public ReaderDaoImpl readerService;
    public GetReaderController(){
        readerService=new ReaderDaoImpl();
    }
    @Override
    public void init(){ }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int page = 1;
            int n;
            int recordsPerPage = 1;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            String email=(request.getParameter("email"));
            List<Reader> list = readerService.findReader(email);
            int noOfRecords = list.size();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("email", email);
            request.setAttribute("list", list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/getBook.jsp").forward(request, response);
    }

}
