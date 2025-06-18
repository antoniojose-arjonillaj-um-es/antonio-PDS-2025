package umu.pds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import modelo.ModoAleatorio;
import modelo.ModoContrarreloj;
import modelo.ModoContrarrelojAleatorio;
import modelo.ModoDefecto;
import modelo.Pregunta;
import modelo.Relleno;

class ModoCursoTest {

    @Test
    void testModoDefecto() {
        ModoDefecto modo = new ModoDefecto();
        List<Pregunta> preguntas = new ArrayList<>(Arrays.asList(
            new Relleno("P1", "seg1", "resp1"),
            new Relleno("P2", "seg2", "resp2")
        ));

        List<Pregunta> copia = new ArrayList<>(preguntas);
        modo.prepararPreguntas(preguntas);
        // No debe modificar la lista
        assertEquals(copia, preguntas);
        assertFalse(modo.usaTemporizador());
        assertEquals("Modalidad defecto", modo.toString());
    }

    @Test
    void testModoAleatorio() {
        ModoAleatorio modo = new ModoAleatorio();
        List<Pregunta> preguntas = new ArrayList<>(Arrays.asList(
            new Relleno("P1", "seg1", "resp1"),
            new Relleno("P2", "seg2", "resp2"),
            new Relleno("P3", "seg3", "resp3")
        ));

        List<Pregunta> copia = new ArrayList<>(preguntas);
        modo.prepararPreguntas(preguntas);

        // Comprobar que todas las preguntas siguen presentes
        assertEquals(copia.size(), preguntas.size());
        assertTrue(preguntas.containsAll(copia), "Lista debe contener todos los elementos originales");
        assertTrue(copia.containsAll(preguntas), "Lista no debe contener elementos extras");
        
        assertFalse(modo.usaTemporizador());
        assertEquals("Modalidad aleatoria", modo.toString());
    }

    @Test
    void testModoContrarreloj() {
        ModoContrarreloj modo = new ModoContrarreloj();
        List<Pregunta> preguntas = new ArrayList<>(Collections.singletonList(
            new Relleno("P1", "seg1", "resp1")
        ));

        modo.prepararPreguntas(preguntas);
        // No modifica la lista
        assertEquals(1, preguntas.size());
        assertTrue(modo.usaTemporizador());
        assertEquals("Modalidad contrarreloj", modo.toString());
    }

    @Test
    void testModoContrarrelojAleatorio() {
        ModoContrarrelojAleatorio modo = new ModoContrarrelojAleatorio();
        List<Pregunta> preguntas = new ArrayList<>(Arrays.asList(
            new Relleno("P1", "seg1", "resp1"),
            new Relleno("P2", "seg2", "resp2"),
            new Relleno("P3", "seg3", "resp3")
        ));

        List<Pregunta> copia = new ArrayList<>(preguntas);
        modo.prepararPreguntas(preguntas);

        // Comprobar que todas las preguntas siguen presentes
        assertEquals(copia.size(), preguntas.size());
        assertTrue(preguntas.containsAll(copia), "Lista debe contener todos los elementos originales");
        assertTrue(copia.containsAll(preguntas), "Lista no debe contener elementos extras");

        assertTrue(modo.usaTemporizador());
        assertEquals("Modalidad contrarreloj aleatorio", modo.toString());
    }
}
