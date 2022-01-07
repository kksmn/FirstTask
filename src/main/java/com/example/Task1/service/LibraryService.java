package com.example.Task1.service;

import com.example.Task1.dao.impl.*;

public class LibraryService {

    ReaderDaoImpl readerService;
    BookDaoImpl bookService;
    OrderDaoImpl orderService;
    AuthorDaoImpl authorService;
    BookCopyDaoImpl bookCopyService;

    public LibraryService() {
        readerService = new ReaderDaoImpl();
        bookService = new BookDaoImpl();
        orderService = new OrderDaoImpl();
        authorService=new AuthorDaoImpl();
        bookCopyService=new BookCopyDaoImpl();
    }

    //?






    /*public void returnBook(Reader reader,String authorName,String bookName,boolean isDefect,String path,Double raiting) throws SQLException, ClassNotFoundException {
        Long readerId=readerService.findReader(reader.getFirstName(),reader.getLastName(),reader.getEmail());
        List<Long> list=authorService.findBookIdByName(authorName);
        Long bookId=null;
        Long copyId=null;
        for (Long id: list) {
            //is null
            //поиск экземпляра
            if(bookService.getBookById(id)!=null && bookService.getBookById(id).equals(bookName)){
                bookId=id;
                copyId=bookCopyService.getCopyByBookId(bookId);
                break;
            }
        }
        List<Reader> readerList=readerService.getReaders();
        if (readerList!= null) {
            for (Reader foundReader : readerList) {
                if (foundReader.getId() == readerId) {
                    List<Order> orderList ;
                    orderList = orderService.getOrdersByReaderId(readerId);
                    if (orderList.size() != 0) {
                        for (Order order : orderList) {
                            //брал ли читатель эту книгу
                            if (order.getCopy_id() == copyId) {
                                //есть ли дефекты
                                if (!isDefect) {
                                    if (bookCopyService.isBookAvailable(copyId)) {
                                        bookCopyService.makeBookAvailable(copyId);
                                        order.setRating(raiting);
                                        if(bookService.getDifferenceBetweenDate(order.getDate())!=0){

                                        }
                                        orderService.deleteOrder();
                                    }
                                } else { }//фотокарточку прикрепи ага и цену увеличь
                            }
                        }
                    }
                } else readerService.signUp(reader);
            }
        }
    }*/
}
