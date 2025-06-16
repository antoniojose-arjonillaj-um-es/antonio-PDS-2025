package modelo;

import java.util.List;

public interface ModoCurso {

	void prepararPreguntas(List<Pregunta> preguntas);

	boolean usaTemporizador();
}