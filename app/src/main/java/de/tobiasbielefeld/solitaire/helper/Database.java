package de.tobiasbielefeld.solitaire.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for database connection
 * @author Koen Pelsmaekers
 * @editor Giani-Luigi Driesen
 */
public enum Database {

    CONNECTION;
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://0.0.0.0:3306/24uloopmodel";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Giani1995";

    private Connection con;

    /**
     * Private constructor for Singleton design pattern
     */
    Database() {
        con = makeConnection();
    }

    /**
     * Getter for the connection field
     * @return The connection object to the MS-SQL server
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * Sets up a connection according to the connection properties given in the properties file
     * @return The connection object
     */
    private Connection makeConnection() {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (Exception e) {
            System.out.println("driver not loaded!");
            e.printStackTrace();
            return con;
        }

        try {
            con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println("no connection!");
            System.out.println(DATABASE_URL);
            e.printStackTrace();
        }
        return con;
    }
}
