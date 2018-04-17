/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcalculator.database.Database;
import jcalculator.database.ScrollbackDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomko
 */
public class DaoTest {
    
    private ScrollbackDao dao;
    private Database db;
    
    public DaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.db = new Database("test.db");
        this.dao = new ScrollbackDao(db);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorWorks() {
        assertNotNull(this.dao);
    }
    
    @Test
    public void insertingWorks() {
        List<String> lista = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lista.add("rivi" + i);
        }
        this.dao.clearAndSave(lista);
        String sb = dao.getScrollback();
        assertEquals("rivi10\nrivi11\nrivi12\nrivi13\nrivi14\nrivi15\nrivi16\nrivi17\nrivi18\nrivi19", sb);
    }
    
    @Test
    public void updateWorks() {
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement("INSERT INTO Scrollback (line) VALUES ('pepsi')");
        } catch (SQLException ex) {
            Logger.getLogger(DaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(dao.executeUpdateStatement(ps));
    }
    
}
