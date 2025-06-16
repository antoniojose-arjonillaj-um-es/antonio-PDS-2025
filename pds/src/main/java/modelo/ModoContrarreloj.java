package modelo;

import java.util.List;

public class ModoContrarreloj implements ModoCurso {

	@Override
	public void prepararPreguntas(List<Pregunta> preguntas) {
		// No hacer nada
	}

	@Override
	public boolean usaTemporizador() {
		return true;
	}

	@Override
	public String toString() {
		return "Modalidad contrarreloj";
	}
}
