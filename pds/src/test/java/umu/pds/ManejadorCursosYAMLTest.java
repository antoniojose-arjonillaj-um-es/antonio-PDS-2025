package umu.pds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Curso;
import modelo.Estado;
import modelo.Pregunta;
import modelo.Traduccion;
import persistencia.ManejadorCursosYAML;

class ManejadorCursosYAMLTest {

	private static final String CURSO_YAML = "nombre: Curso de prueba\n" + "preguntas: []\n" + "estado: SIN_EMPEZAR\n"
			+ "contestadas: 0\n" + "correcciones: []\n";

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
		ManejadorCursosYAML instancia1 = ManejadorCursosYAML.getInstancia();
		ManejadorCursosYAML instancia2 = ManejadorCursosYAML.getInstancia();
		assertNotNull(instancia1);
		assertSame(instancia1, instancia2, "Debe ser el mismo singleton");
	}

	@Test
	void testImportarCursosFichero() throws Exception {
		ManejadorCursosYAML importador = ManejadorCursosYAML.getInstancia();

		Curso curso = importador.importarCursosFichero(tempFile);
		assertNotNull(curso);
		assertEquals("Curso de prueba", curso.getNombre());
		assertNotNull(curso.getPreguntas());
		assertEquals(0, curso.getPreguntas().size());
		assertEquals(Estado.SIN_EMPEZAR, curso.getEstado());
	}

	@Test
	void testImportarCursosRutaArchivo() throws Exception {
		ManejadorCursosYAML importador = ManejadorCursosYAML.getInstancia();

		Curso curso = importador.importarCursosRuta(tempFile.getAbsolutePath());
		assertNotNull(curso);
		assertEquals("Curso de prueba", curso.getNombre());
		assertNotNull(curso.getPreguntas());
		assertEquals(0, curso.getPreguntas().size());
		assertEquals(Estado.SIN_EMPEZAR, curso.getEstado());
	}

	@Test
	public void testExportarCurso() throws Exception {
		// Crear curso de prueba
		Pregunta p1 = new Traduccion("Hola", "Hello");
		Pregunta p2 = new Traduccion("Adiós", "Goodbye");
		List<Pregunta> preguntas = Arrays.asList(p1, p2);
		Curso curso = new Curso("Curso Prueba", preguntas);

		// Directorio temporal
		File tempDir = new File("test-exports");
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}

		// Exportar curso
		ManejadorCursosYAML importer = ManejadorCursosYAML.getInstancia();
		importer.exportarCurso(curso, tempDir.getAbsolutePath());

		// Nombre esperado del archivo
		File archivo = new File(tempDir, "Curso-Prueba.yaml");

		// Comprobar que el archivo existe y no está vacío
		assertTrue(archivo.exists(), "El archivo YAML debería haberse creado");
		assertTrue(archivo.length() > 0, "El archivo YAML no debería estar vacío");

		// Limpieza opcional
		archivo.delete();
		tempDir.delete();
	}
}
