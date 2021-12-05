package com.example.Task1.servlet;

import com.example.Task1.service.Numbers;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet("")
public class MainServlet extends HttpServlet {

    public MainServlet(){}

    @Override
    public void init(){ }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("list", Numbers.getNumbers());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("list", Numbers.getSortedNumbers());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
    @Override
    public void destroy() {
    }
}