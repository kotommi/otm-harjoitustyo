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
    public void constructor() {
        assertNotNull(this.converter);
        assertEquals(converter.getFrom(), Encoding.DECIMAL);
        assertEquals(converter.getTo(), Encoding.BINARY);
        assertEquals(converter.getEndian(), Endian.LITTLE);
    }
}
