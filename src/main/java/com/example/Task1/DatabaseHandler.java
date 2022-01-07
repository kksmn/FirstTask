package com.example.Task1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends  Configs {
    Connection dbConnection;
    public Connection getDbConnection()
                throws ClassNotFoundException,SQLException{
        String connectionString="jdbc:postgresql://localhost:5432/postgres";
        Class.forName("org.postgresql.Driver");
        dbConnection=DriverManager.getConnection(connectionString, dbUser,dbPassword);
        return dbConnection;

    }

}
