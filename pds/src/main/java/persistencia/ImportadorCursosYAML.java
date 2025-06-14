package persistencia;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import modelo.Curso;

public class ImportadorCursosYAML {

	private static ImportadorCursosYAML instancia;

	// Constructor
	private ImportadorCursosYAML() {

	}

	// Métodos de clase
	public static ImportadorCursosYAML getInstancia() {
		if (instancia == null) {
			instancia = new ImportadorCursosYAML();
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
}