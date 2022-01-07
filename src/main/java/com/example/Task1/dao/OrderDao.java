package com.example.Task1.dao;

import com.example.Task1.models.Order;
import com.example.Task1.models.Reader;

public interface OrderDao {
    void addNewOrder(Order order);
    boolean getReaderDebt(Reader reader);
    int countOrders(Long readerId);


}
