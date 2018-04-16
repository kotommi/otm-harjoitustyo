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
 *
 * @author tomko
 */
public class ScrollbackDao {

    private Database db;
    private Connection conn;

    public ScrollbackDao() {
        this.db = new Database();
        try {
            this.db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Scrollback (id integer, line varchar(999))").execute();
        } catch (SQLException ex) {
            logError(ex);
        }
    }

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
     * Clears the scrollback and saves with lines
     *
     * @param lines List of lines from resultArea
     */
    public void clearAndSave(List<String> lines) {
        try {
            conn = db.getConnection();

            PreparedStatement delete = conn.prepareStatement("DELETE FROM Scrollback");
            executeUpdateStatement(delete);
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
     * Executes an update statement with logging
     *
     * @param ps
     */
    public void executeUpdateStatement(PreparedStatement ps) {
        try {
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            logError(ex);
        }
    }

    public void logError(SQLException ex) {
        Logger.getLogger(ScrollbackDao.class.getName()).log(Level.SEVERE, null, ex);
    }

}
