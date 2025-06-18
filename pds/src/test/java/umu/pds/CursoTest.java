package umu.pds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Curso;
import modelo.Estado;
import modelo.Pregunta;
import modelo.Relleno;

public class CursoTest {

    private Curso curso;
    private Pregunta pregunta1, pregunta2, pregunta3;

    @BeforeEach
    void setUp() {
        // Instancias dummy de Pregunta usando clases concretas (puedes usar Relleno)
        pregunta1 = new Relleno("Pregunta 1", "seg1", "resp1");
        pregunta2 = new Relleno("Pregunta 2", "seg2", "resp2");
        pregunta3 = new Relleno("Pregunta 3", "seg3", "resp3");

        List<Pregunta> listaPreguntas = Arrays.asList(pregunta1, pregunta2, pregunta3);
        curso = new Curso("Curso Prueba", listaPreguntas);
    }

    @Test
    void testConstructorYGetters() {
        assertEquals("Curso Prueba", curso.getNombre());
        assertEquals(3, curso.getPreguntas().size());
        assertEquals(3, curso.getNumPreguntas());
        assertEquals(Estado.SIN_EMPEZAR, curso.getEstado());
        assertEquals(0, curso.getContestadas());
        assertNotNull(curso.getCorrecciones());
        assertEquals(3, curso.getCorrecciones().size());
        assertTrue(curso.getCorrecciones().stream().allMatch(Objects::isNull));
    }

    @Test
    void testSetters() {
        curso.setNombre("Nuevo Nombre");
        assertEquals("Nuevo Nombre", curso.getNombre());

        List<Pregunta> nuevasPreguntas = Collections.singletonList(pregunta1);
        curso.setPreguntas(nuevasPreguntas);
        assertEquals(1, curso.getPreguntas().size());

        List<Boolean> correcciones = Arrays.asList(true, false);
        curso.setCorrecciones(correcciones);
        assertEquals(correcciones, curso.getCorrecciones());

        curso.setContestadas(2);
        assertEquals(2, curso.getContestadas());

        curso.setEstado(Estado.FINALIZADO);
        assertEquals(Estado.FINALIZADO, curso.getEstado());
    }

    @Test
    void testGetCorrectasYIncorrectas() {
        List<Boolean> correcciones = Arrays.asList(true, false, null);
        curso.setCorrecciones(correcciones);

        assertEquals(1, curso.getCorrectas());
        assertEquals(2, curso.getIncorrectas()); // porque null también cuenta como incorrecto
    }

    @Test
    void testGetResultados() {
        curso.setCorrecciones(Arrays.asList(true, false, true));
        String expected = "Correctas: 2\nIncorrectas: 1";
        assertEquals(expected, curso.getResultados());
    }

    @Test
    void testIniciarPausarTerminar() {
        curso.iniciar();
        assertEquals(Estado.EN_PROCESO, curso.getEstado());

        curso.pausar();
        assertEquals(Estado.PAUSADO, curso.getEstado());

        curso.setCorrecciones(Arrays.asList(true, false, true));
        String resultado = curso.terminar();
        assertEquals(Estado.FINALIZADO, curso.getEstado());
        assertTrue(resultado.contains("Correctas"));
        assertTrue(resultado.contains("Incorrectas"));
    }

    @Test
    void testGetPreguntasVacias() {
        // Al principio todas vacías
        List<Pregunta> vacias = curso.getPreguntasVacias();
        assertEquals(3, vacias.size());

        // Marcar la primera pregunta como corregida
        List<Boolean> correcciones = Arrays.asList(true, null, null);
        curso.setCorrecciones(correcciones);

        vacias = curso.getPreguntasVacias();
        assertEquals(2, vacias.size());
        assertFalse(vacias.contains(pregunta1));
    }

    @Test
    void testCorregirPregunta() {
        curso.corregirPregunta(pregunta2, "resp2");
        assertEquals(1, curso.getContestadas());
        List<Boolean> correcciones = curso.getCorrecciones();
        int idx = curso.getPreguntas().indexOf(pregunta2);
        assertTrue(correcciones.get(idx));

        // Corregir con respuesta incorrecta
        curso.corregirPregunta(pregunta1, "respuesta incorrecta");
        assertEquals(2, curso.getContestadas());
        idx = curso.getPreguntas().indexOf(pregunta1);
        assertFalse(curso.getCorrecciones().get(idx));
    }

    @Test
    void testReiniciarCurso() {
        curso.setEstado(Estado.FINALIZADO);
        curso.setContestadas(3);
        curso.setCorrecciones(Arrays.asList(true, false, true));

        curso.reiniciarCurso();

        assertEquals(Estado.SIN_EMPEZAR, curso.getEstado());
        assertEquals(0, curso.getContestadas());
        assertTrue(curso.getCorrecciones().stream().allMatch(Objects::isNull));
    }

    @Test
    void testToString() {
        assertEquals("Curso Prueba", curso.toString());
    }
}
