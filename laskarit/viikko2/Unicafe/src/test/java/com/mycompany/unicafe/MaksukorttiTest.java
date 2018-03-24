package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void rahanLataaminenToimiiOikein() {
        kortti.lataaRahaa(100);
        assertEquals(110, kortti.saldo());
    }

    @Test
    public void saldoVaheneeOikein() {
        kortti.lataaRahaa(990);
        kortti.otaRahaa(100);
        assertEquals(900, kortti.saldo());
    }

    @Test
    public void saldoEiMuutuJosEiRahaa() {
        kortti.otaRahaa(42);
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void otaReturnValueToimii() {
        assertTrue(kortti.otaRahaa(10));
    }

    @Test
    public void otaFalseToimii() {
        assertFalse(kortti.otaRahaa(1000));
    }

    @Test
    public void toStringToimii() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
