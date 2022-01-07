package com.example.Task1.controller;

import com.example.Task1.models.Reader;
import com.example.Task1.service.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/returnBook")
public class ReturnBookController extends HttpServlet {

    LibraryService libraryService;
    public ReturnBookController(){
        libraryService=new LibraryService();
    }

    @Override
    public void init(){ }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/returnBook.jsp").forward(request, response);
    }
    @Override
    //author
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Reader reader = new Reader();
            Double raiting = 0.0;
            raiting = Double.valueOf(request.getParameter("raiting"));
            reader.setFirstName(request.getParameter("firstName"));
            reader.setLastName(request.getParameter("lastName"));
            reader.setEmail(request.getParameter("email"));
            String bookName = request.getParameter("russianname");
            String authorName = request.getParameter("authorName");
            Boolean flag = Boolean.valueOf(request.getParameter("defect"));
            String imagePath = request.getParameter("image");
            /*book.returnBook(reader,authorName,bookName,flag,imagePath,raiting);
             */
        /*} catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        } finally {

        }
    }
    @Override
    public void destroy() {
    }
}
