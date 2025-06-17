package modelo;

import java.util.Collections;
import java.util.List;

public class ModoAleatorio implements ModoCurso {

	@Override
	public void prepararPreguntas(List<Pregunta> preguntas) {
		Collections.shuffle(preguntas);
	}

	@Override
	public boolean usaTemporizador() {
		return false;
	}

	@Override
	public String toString() {
		return "Modalidad aleatoria";
	}
}