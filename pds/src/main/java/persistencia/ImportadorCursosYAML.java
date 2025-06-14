package persistencia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import modelo.Curso;

import java.io.File;
import java.util.List;

public class ImportadorCursosYAML {
    public static List<Curso> importarCursos(String rutaArchivo) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readerForListOf(Curso.class).readValue(new File(rutaArchivo));
    }
}