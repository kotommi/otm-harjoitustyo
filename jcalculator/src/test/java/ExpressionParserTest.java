/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void emptyStringWorks() {
        assertTrue(ExpressionParser.checkParenthesis(""));
    }
    
    @Test
    public void complicatedExpressionWorks() {
        assertTrue(ExpressionParser.checkParenthesis("(x + 1)(-[y^2]{})"));
        assertTrue(ExpressionParser.checkParenthesis("(setf (fdefinition 'f) #'(lambda (a) (block f b...)))"));
        assertFalse(ExpressionParser.checkParenthesis("()()({{{}{}{}}}[][][][]"));
    }
}
