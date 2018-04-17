/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for generating a database connection.
 *
 * @author tomko
 */
public class Database {

    private final String dbName;

    /**
     * Default constructor that stores the dbName
     *
     * @param dbName
     */
    public Database(String dbName) {
        this.dbName = dbName;
    }

    /**
     * creates a connection to a database with DriverManager.
     *
     * @return returns a connection to the database or null if there was an
     * error
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + dbName);
        } catch (SQLException e) {
            return null;
        }
    }

}
