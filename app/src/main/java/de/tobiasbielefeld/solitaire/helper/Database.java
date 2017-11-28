package de.tobiasbielefeld.solitaire.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for database connection
 * @author Koen Pelsmaekers
 * @editor Giani-Luigi Driesen
 */

// @GN
public enum Database {

    CONNECTION;
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://10.0.2.2:3306/solitairedb"; // het IP adres was niet juist, dat moet 10.0.2.2 zijn omdat we met een emulator bezig zijn
    private static final String DATABASE_USERNAME = "Giani";
    private static final String DATABASE_PASSWORD = "Giani1995";

    public Connection con;

    /**
     * Private constructor for Singleton design pattern
     */
    Database() {
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
    public void makeConnection() {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (Exception e) {
            System.out.println("driver not loaded!");
            e.printStackTrace();
            return;
        }

        try {
            con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            System.out.println("Connectie: "+con);
        } catch (SQLException e) {
            System.out.println("no connection!");
            System.out.println(DATABASE_URL);
            e.printStackTrace();
        }
        System.out.println("Connection succesfull");
    }
}
