/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(10 * 100);
    }

    @After
    public void tearDown() {
    }

    //luodun kassapäätteen rahamäärä ja myytyjen lounaiden määrä on oikea (rahaa 1000, lounaita myyty 0)
    @Test
    public void konstruktoriToimii() {
        assertEquals(1000 * 100, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }

    /**
     * käteisosto toimii sekä edullisten että maukkaiden lounaiden osalta
     *
     * jos maksu riittävä: kassassa oleva rahamäärä kasvaa lounaan hinnalla ja
     * vaihtorahan suuruus on oikea jos maksu on riittävä: myytyjen lounaiden
     * määrä kasvaa jos maksu ei ole riittävä: kassassa oleva rahamäärä ei
     * muutu, kaikki rahat palautetaan vaihtorahana ja myytyjen lounaiden
     * määrässä ei muutosta
     *
     *
     */
    @Test
    public void edullinenMaksuToimii() {
        int vaihtoraha = paate.syoEdullisesti(300);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullinenVaihtorahaToimii() {
        int vaihtoraha = paate.syoEdullisesti(300);
        assertEquals(60, vaihtoraha);
    }

    @Test
    public void liianPieniEdullinenEiToimi() {
        int vaihtoraha = paate.syoEdullisesti(10);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(10, vaihtoraha);
    }

    @Test
    public void maukasMaksuToimii() {
        int vaihtoraha = paate.syoMaukkaasti(500);
        assertEquals(100, vaihtoraha);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void liianPieniMaukasEiToimi() {
        int vaihtoraha = paate.syoMaukkaasti(10);
        assertEquals(10, vaihtoraha);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    /**
     * korttiosto toimii sekä edullisten että maukkaiden lounaiden osalta
     *
     * jos kortilla on tarpeeksi rahaa, veloitetaan summa kortilta ja
     * palautetaan true jos kortilla on tarpeeksi rahaa, myytyjen lounaiden
     * määrä kasvaa jos kortilla ei ole tarpeeksi rahaa, kortin rahamäärä ei
     * muutu, myytyjen lounaiden määrä muuttumaton ja palautetaan false kassassa
     * oleva rahamäärä ei muutu kortilla ostettaessa
     *
     * kortille rahaa ladattaessa kortin saldo muuttuu ja kassassa oleva
     * rahamäärä kasvaa ladatulla summalla *
     */
    @Test
    public void kassanKateinenEiMuutu() {
        int saldo = paate.kassassaRahaa();
        paate.syoMaukkaasti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals(saldo, paate.kassassaRahaa());
    }

    @Test
    public void korttiEdullinenToimii() {
        boolean onnistui = paate.syoEdullisesti(kortti);
        assertTrue(onnistui);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void korttiEiRahaaEdullinenToimii() {
        kortti.otaRahaa(kortti.saldo() - 100);
        boolean onnistui = paate.syoEdullisesti(kortti);
        assertFalse(onnistui);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void korttiMaukasToimii() {
        boolean onnistui = paate.syoMaukkaasti(kortti);
        assertTrue(onnistui);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void korttiEiRahaaMaukasToimii() {
        kortti.otaRahaa(kortti.saldo() - 100);
        boolean onnistui = paate.syoMaukkaasti(kortti);
        assertFalse(onnistui);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void kortinLatausToimii() {
        int alkusaldo = paate.kassassaRahaa();
        int korttialku = kortti.saldo();
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(alkusaldo + 100, paate.kassassaRahaa());
        assertEquals(korttialku + 100, kortti.saldo());
    }

    @Test
    public void eiVoiLadataNegatiivista() {
        int alkusaldo = paate.kassassaRahaa();
        int korttialku = kortti.saldo();
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(alkusaldo, paate.kassassaRahaa());
        assertEquals(korttialku, kortti.saldo());
    }
}
