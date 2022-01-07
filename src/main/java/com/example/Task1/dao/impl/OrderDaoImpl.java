package com.example.Task1.dao.impl;

import com.example.Task1.dao.OrderDao;
import com.example.Task1.dao.impl.QueryExecutor;
import com.example.Task1.dao.pool.ConnectionPool;
import com.example.Task1.models.Order;
import com.example.Task1.models.Reader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private QueryExecutor executor = QueryExecutor.getInstance();
    public OrderDaoImpl() {

    }

    public void addNewOrder(Order order) {
        String sql = "INSERT INTO Orders(readerId, price,copyId,fine,date) VALUES (?, ?, ?,?,?)";
        try {
            executor.executeStatement(sql);
            } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteOrder(Integer id) {
        String sql = "DELETE FROM Orders WHERE copy_id=?";
        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrdersByReaderId(Long id) {
        String sql = "SELECT copy FROM Orders copy WHERE copy.readerid=?";
        List<Order> ordersId = new ArrayList<Order>();
        try (PreparedStatement stmt = ConnectionPool.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                ordersId.add((Order) resultSet.getObject("copy"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ordersId;

    }

    public boolean getReaderDebt(Reader reader) {
        String sql = "SELECT * FROM Orders WHERE readerid=?";
        List<Order> orders = new ArrayList<Order>();
        try {
            ResultSet resultSet = executor.getResultSet(sql,reader.getId());
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int countOrders(Long readerId) {
        String sql = "SELECT row_count FROM Orders WHERE readerid=?";
        int count = 0;
        try {
            ResultSet resultSet = executor.getResultSet(sql,readerId);
            while (resultSet.next()) {
                count = resultSet.getInt("row_count");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
