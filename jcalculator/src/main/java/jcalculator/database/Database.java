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
 *
 * @author tomko
 */
public class Database {

    public Connection getConnection() {
        //fetch this from config
        String dbString = "scrollback.db";
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + dbString);
        } catch (SQLException e) {
            return null;
        }
    }

}
