package test.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fathzer.soft.javaluator.DoubleEvaluator;
import jcalculator.util.ExpressionParser;
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
public class ExpressionParserTest {

    DoubleEvaluator de;

    public ExpressionParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        de = new DoubleEvaluator();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void emptyStringWorks() {
        try {
            assertTrue(de.evaluate("") == 0);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void simpleExpressions() {
        assertTrue(2 == de.evaluate("1+1"));
        assertTrue(de.evaluate("2^0") == 1);
    }
}
