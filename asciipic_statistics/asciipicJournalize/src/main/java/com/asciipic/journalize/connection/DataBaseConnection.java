package com.asciipic.journalize.connection;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnection {

    //Alexandra Folvaiter: fix me!
    //take this information from a config file
    private static String jdbcUrl = "jdbc:oracle:thin:ASCIIPIC@//localhost:1521";
    private static String userId = "ASCIIPIC";
    private static String password = "ASCIIPIC";
    Connection connection;
    OracleDataSource dataSource;

    private static DataBaseConnection ourInstance = new DataBaseConnection();

    public static DataBaseConnection getInstance() {
        return ourInstance;
    }

    private DataBaseConnection() {
        try {
            dataSource = new OracleDataSource();
            dataSource.setURL(jdbcUrl);
            connection = dataSource.getConnection(userId, password);
        } catch (SQLException sqlException) {
            System.out.println("There was an error during the initialization of the DB connection");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
