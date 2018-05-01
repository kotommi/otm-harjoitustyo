package test.domain.conversion;

import jcalculator.domain.conversion.Converter;
import jcalculator.domain.conversion.Encoding;
import jcalculator.domain.conversion.Endian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {
    
    private Converter converter;
    
    public ConverterTest() {
    }
    
    @Before
    public void setUp() {
        this.converter = new Converter();
    }
    
    @Test
    public void constructorAndGetters() {
        assertNotNull(this.converter);
        assertEquals(converter.getFrom(), Encoding.DECIMAL);
        assertEquals(converter.getTo(), Encoding.BINARY);
        assertEquals(converter.getEndian(), Endian.LITTLE);
        assertEquals(converter.getEndianTo(), Endian.LITTLE);
    }
    
    @Test
    public void setters() {
        this.converter.setFrom(Encoding.HEX);
        assertEquals(converter.getFrom(), Encoding.HEX);
        this.converter.setTo(Encoding.DECIMAL);
        assertEquals(converter.getTo(), Encoding.DECIMAL);
        this.converter.setEndian(Endian.BIG);
        assertEquals(converter.getEndian(), Endian.BIG);
        this.converter.setEndianTo(Endian.BIG);
        assertEquals(converter.getEndianTo(), Endian.BIG);
    }
    
    @Test
    public void convertDecBin() {
        String input = "10";
        String result = converter.convert(input);
        assertEquals("1010", result);
    }
    
    @Test
    public void convertDecBinBigEndian() {
        converter.setEndianTo(Endian.BIG);
        converter.setTo(Encoding.BINARY);
        String input = "10";
        String result = converter.convert(input);
        String expected = "1010000000000000000000000000000";
        assertEquals("result length: " + result.length() + ", expected: " + expected.length(), result, expected);
    }
    
    @Test
    public void convertBinDec() {
        String input = "1010";
        converter.setFrom(Encoding.BINARY);
        converter.setTo(Encoding.DECIMAL);
        String result = converter.convert(input);
        assertEquals("10", result);
    }
    
    @Test
    public void convertHexDec() {
        String input = "FF";
        converter.setFrom(Encoding.HEX);
        converter.setTo(Encoding.DECIMAL);
        String result = converter.convert(input);
        assertEquals("255", result);
    }
    
    @Test
    public void convertHexDecV2() {
        String input = "0xFF";
        converter.setFrom(Encoding.HEX);
        converter.setTo(Encoding.DECIMAL);
        String result = converter.convert(input);
        assertEquals("255", result);
    }
    
    @Test
    public void errors() {
        String result = converter.convert("aaba");
        assertEquals("NaN", result);
        converter.setFrom(Encoding.HEX);
        result = converter.convert("boring");
        assertEquals("NaN", result);
        converter.setFrom(Encoding.BINARY);
        result = converter.convert("358");
        assertEquals("NaN", result);
    }
    
    @Test
    public void reverseHexEndian() {
        converter.setFrom(Encoding.HEX);
        converter.setTo(Encoding.HEX);
        converter.setEndian(Endian.BIG);
        String input = "FF";
        String result = converter.convert(input);
        assertEquals("ff00000", result);
    }
}
