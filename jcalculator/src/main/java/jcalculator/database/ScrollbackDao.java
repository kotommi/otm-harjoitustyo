/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DatabaseAccessObject class for saving and loading scrollback history.
 *
 * @author tomko
 */
public class ScrollbackDao {

    private Database db;
    private Connection conn;

    /**
     * Default constructor that initializes a database connection object and
     * creates the necessary table.
     *
     * @param db a Database object
     */
    public ScrollbackDao(Database db) {
        this.db = db;
        try {
            this.db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Scrollback (id integer PRIMARY KEY, line varchar(999))").execute();
        } catch (SQLException ex) {
            logError(ex);
        }
    }

    /**
     * Fetches scrollback from a database and converts it into a string tailored
     * for TextArea.
     *
     * @return a string with lines separated with \n
     */
    public String getScrollback() {
        conn = db.getConnection();
        StringBuilder lines = new StringBuilder();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Scrollback;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String line = rs.getString("line");
                lines.append("\n" + line);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            logError(ex);
        }
        return lines.toString().trim();
    }

    /**
     * Clears the scrollback and saves last x lines in the database.
     *
     * @param lines List of lines from resultArea
     */
    public void clearAndSave(List<String> lines) {
        try {
            conn = db.getConnection();

            PreparedStatement delete = conn.prepareStatement("DELETE FROM Scrollback");
            executeUpdateStatement(delete);
            //put this in a config
            if (lines.size() > 10) {
                lines = lines.subList(lines.size() - 10, lines.size());
            }
            for (String line : lines) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Scrollback (line) VALUES (?)");
                ps.setString(1, line);
                executeUpdateStatement(ps);
            }
            conn.close();
        } catch (SQLException ex) {
            logError(ex);
        }

    }

    /**
     * Executes an update statement with logging.
     *
     * @param ps PreparedStatement to execute
     * @return true if update was successful and of correct type.
     */
    public boolean executeUpdateStatement(PreparedStatement ps) {
        try {
            boolean ret = ps.execute();
            ps.close();
            return !ret;
        } catch (SQLException ex) {
            logError(ex);
        }
        return false;
    }

    /**
     * Handles exceptions with Logger.
     *
     * @param ex a SQLException thrown by another method
     */
    private void logError(SQLException ex) {
        Logger.getLogger(ScrollbackDao.class.getName()).log(Level.SEVERE, null, ex);
    }

}
