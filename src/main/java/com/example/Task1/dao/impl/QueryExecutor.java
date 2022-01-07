package com.example.Task1.dao.impl;

import com.example.Task1.dao.pool.ConnectionPool;

import java.sql.*;

public class QueryExecutor {

    //private static final Logger LOGGER = LogManager.getLogger(QueryExecutor.class);

    private Connection connection = getConnection();
    private PreparedStatement preparedStatement;

    private static QueryExecutor instance = null;

    private Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    private QueryExecutor() {
    }

    public static QueryExecutor getInstance() {
        if (instance == null)
            instance = new QueryExecutor();
        return instance;
    }

    private void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException, SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    public int executeStatement(String query, Object... args) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setValues(preparedStatement, args);
            int res = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return res;
            }
        } catch (SQLException e) {
            //	LOGGER.error("Execute statement error " + e.getMessage());
        }
        return 0;
    }


    public ResultSet getResultSet(String query, Object... args) throws SQLException {
        preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        setValues(preparedStatement, args);
        return preparedStatement.executeQuery();
    }


    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            //LOGGER.error("Error while closing connection");
        }
    }
}
