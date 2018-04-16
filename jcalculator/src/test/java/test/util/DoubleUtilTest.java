/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.util;

import jcalculator.util.DoubleUtil;
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
public class DoubleUtilTest {
    
    public DoubleUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void returnsIntString() {
        assertEquals("1", DoubleUtil.toString(1.00000000000d));
    }
    
    @Test
    public void intStringDecimalTest() {
        assertNotEquals("1", Double.toString(1.000001));
    }
    
    @Test
    public void decimalFormattingTest() {
        double d = 1d/3d;
        assertEquals("0.333333", DoubleUtil.toString(d));
    }
    
}
