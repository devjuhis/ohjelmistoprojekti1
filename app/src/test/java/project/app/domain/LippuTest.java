package project.app.domain;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import project.app.domain.Hinnasto;
import project.app.domain.Lippu;
import project.app.domain.Maksutapahtuma;
import project.app.domain.Tapahtuma;

public class LippuTest {

    private Lippu lippu;
    private Tapahtuma tapahtuma;
    private Hinnasto hinnasto;
    private Maksutapahtuma maksutapahtuma;

    @BeforeEach
    public void setUp() {
        tapahtuma = Mockito.mock(Tapahtuma.class); 
        hinnasto = Mockito.mock(Hinnasto.class); 
        maksutapahtuma = Mockito.mock(Maksutapahtuma.class); 
        lippu = new Lippu(tapahtuma, hinnasto);
    }

    @Test
    public void TestKoodi() {
        lippu.prePersist();

        assertNotNull(lippu.getKoodi());
        assertNotNull(lippu.getLuontiaika());
    }

    @Test
    public void TestConstructor() {
        assertEquals(tapahtuma, lippu.getTapahtuma());
        assertEquals(hinnasto, lippu.getHinnasto());
    }

    @Test
    public void TestKaytetty() {
        lippu.setKaytetty(true);
        assertTrue(lippu.getKaytetty());
        
        lippu.setKaytetty(false);
        assertFalse(lippu.getKaytetty());
    }

    @Test
    public void TestMaara() {
        lippu.setMaara(1);
        assertEquals(1, lippu.getMaara());
        
        lippu.setMaara(-1);
        assertEquals(-1, lippu.getMaara());
    }
}

