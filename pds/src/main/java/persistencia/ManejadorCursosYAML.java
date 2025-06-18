package persistencia;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import modelo.Curso;

public class ManejadorCursosYAML {

	private static ManejadorCursosYAML instancia;

	// Constructor
	private ManejadorCursosYAML() {

	}

	// Métodos de clase
	public static ManejadorCursosYAML getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorCursosYAML();
		}
		return instancia;
	}

	public Curso importarCursosRuta(String rutaArchivo) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		return mapper.readValue(new File(rutaArchivo), Curso.class);
	}

	public Curso importarCursosFichero(File archivo) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		return mapper.readValue(archivo, Curso.class);
	}

	public void exportarCurso(Curso curso, String rutaDirectorio) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.findAndRegisterModules();

		// Aseguramos que el nombre del archivo es válido
		String nombreArchivo = curso.getNombre().replaceAll("[^\\p{L}\\p{N} _\\-\\.]", "-") + ".yaml";
		File archivo = new File(rutaDirectorio, nombreArchivo);

		mapper.writeValue(archivo, curso);
	}
}