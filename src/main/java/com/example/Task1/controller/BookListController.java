package com.example.Task1.controller;

import com.example.Task1.dao.impl.BookDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findBook")
public class BookListController extends HttpServlet {

    public BookDaoImpl bookService;
    public BookListController(){
        bookService=new BookDaoImpl();
    }
    @Override
    public void init(){ }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*request.setCharacterEncoding("UTF-8");
        Map<Long, Book> bookMap=bookService.findBookByName(request.getParameter("russianname"));
        request.setAttribute("bookMap", bookMap);
        getServletContext().getRequestDispatcher("/searchPage.jsp").forward(request, response);
*/    }

}
