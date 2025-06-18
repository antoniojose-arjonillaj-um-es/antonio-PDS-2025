package umu.pds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Relleno;
import modelo.Traduccion;

public class PreguntaTest {

	 private Relleno relleno;
	    private Traduccion traduccion;
	    private modelo.Test testPregunta;

    @BeforeEach
    void setUp() {
        relleno = new Relleno("Capital de Francia", "es", "París");
        traduccion = new Traduccion("Casa", "House");
        List<String> opciones = Arrays.asList("Rojo", "Azul", "Verde", "Amarillo");
        List<Integer> respuestas = Arrays.asList(1, 3); // Azul, Amarillo
        testPregunta = new modelo.Test("Colores primarios", opciones, respuestas);
    }

    // ==== TEST RELLENO ====

    @Test
    void testRellenoConstructorYGetters() {
        assertEquals("Capital de Francia", relleno.getPregunta());
        assertEquals("es", relleno.getSegundaPreg());
        assertEquals("París", relleno.getRespuesta());
    }

    @Test
    void testRellenoCorregirCorrecto() {
        assertTrue(relleno.corregir("parís"));   // ignore case
        assertTrue(relleno.corregir("PARÍS"));
    }

    @Test
    void testRellenoCorregirIncorrecto() {
        assertFalse(relleno.corregir("Paris"));   // sin tilde
        assertFalse(relleno.corregir(" París"));  // espacio
    }

    @Test
    void testRellenoSetters() {
        relleno.setRespuesta("Madrid");
        relleno.setSegundaPreg("en");
        assertEquals("Madrid", relleno.getRespuesta());
        assertEquals("en", relleno.getSegundaPreg());
    }

    // ==== TEST TRADUCCIÓN ====

    @Test
    void testTraduccionConstructorYGetters() {
        assertEquals("Casa", traduccion.getPregunta());
        assertEquals("House", traduccion.getRespuesta());
    }

    @Test
    void testTraduccionCorregirExacto() {
        assertTrue(traduccion.corregir("House"));
    }

    @Test
    void testTraduccionCorregirIncorrecto() {
        assertFalse(traduccion.corregir("Home"));
    }

    @Test
    void testTraduccionSetters() {
        traduccion.setRespuesta("Home");
        assertEquals("Home", traduccion.getRespuesta());
    }

    // ==== TEST DE TIPO TEST ====

    @Test
    void testTestConstructorYGetters() {
        assertEquals("Colores primarios", testPregunta.getPregunta());
        assertEquals(Arrays.asList("Rojo", "Azul", "Verde", "Amarillo"), testPregunta.getOpciones());
        assertEquals(Arrays.asList(1, 3), testPregunta.getRespuestas());
    }

    @Test
    void testTestCorregirCorrecto() {
        assertTrue(testPregunta.corregir("1,3"));
        assertTrue(testPregunta.corregir("3,1")); // diferente orden
    }

    @Test
    void testTestCorregirIncorrecto() {
        assertFalse(testPregunta.corregir("1,2,3")); // respuesta extra
        assertFalse(testPregunta.corregir("1"));     // incompleta
        assertFalse(testPregunta.corregir(""));      // vacía
        assertFalse(testPregunta.corregir(null));    // nula
    }

    @Test
    void testTestSetters() {
        testPregunta.setOpciones(Arrays.asList("Uno", "Dos"));
        testPregunta.setRespuestas(Arrays.asList(0));
        assertEquals(Arrays.asList("Uno", "Dos"), testPregunta.getOpciones());
        assertEquals(Arrays.asList(0), testPregunta.getRespuestas());
    }
}

