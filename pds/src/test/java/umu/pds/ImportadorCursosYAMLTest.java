package umu.pds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Curso;
import modelo.Estado;
import persistencia.ImportadorCursosYAML;

class ImportadorCursosYAMLTest {

    private static final String CURSO_YAML = 
        "nombre: Curso de prueba\n" +
        "preguntas: []\n" +
        "estado: SIN_EMPEZAR\n" +
        "contestadas: 0\n" +
        "correcciones: []\n";

    private File tempFile;

    @BeforeEach
    void setUp() throws Exception {
        // Crear archivo temporal con contenido YAML
        tempFile = File.createTempFile("curso-test", ".yaml");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(CURSO_YAML);
        }
        tempFile.deleteOnExit();
    }

    @Test
    void testGetInstanciaSingleton() {
        ImportadorCursosYAML instancia1 = ImportadorCursosYAML.getInstancia();
        ImportadorCursosYAML instancia2 = ImportadorCursosYAML.getInstancia();
        assertNotNull(instancia1);
        assertSame(instancia1, instancia2, "Debe ser el mismo singleton");
    }

    @Test
    void testImportarCursosFichero() throws Exception {
        ImportadorCursosYAML importador = ImportadorCursosYAML.getInstancia();

        Curso curso = importador.importarCursosFichero(tempFile);
        assertNotNull(curso);
        assertEquals("Curso de prueba", curso.getNombre());
        assertNotNull(curso.getPreguntas());
        assertEquals(0, curso.getPreguntas().size());
        assertEquals(Estado.SIN_EMPEZAR, curso.getEstado());
    }

    @Test
    void testImportarCursosRutaArchivo() throws Exception {
        ImportadorCursosYAML importador = ImportadorCursosYAML.getInstancia();

        Curso curso = importador.importarCursosRuta(tempFile.getAbsolutePath());
        assertNotNull(curso);
        assertEquals("Curso de prueba", curso.getNombre());
        assertNotNull(curso.getPreguntas());
        assertEquals(0, curso.getPreguntas().size());
        assertEquals(Estado.SIN_EMPEZAR, curso.getEstado());
    }
}

