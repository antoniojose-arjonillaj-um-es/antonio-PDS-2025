package modelo;

import java.util.Collections;
import java.util.List;

public class ModoContrarrelojAleatorio implements ModoCurso {

	@Override
	public void prepararPreguntas(List<Pregunta> preguntas) {
		Collections.shuffle(preguntas);
	}

	@Override
	public boolean usaTemporizador() {
		return true;
	}

	@Override
	public String toString() {
		return "Modalidad contrarreloj aleatorio";
	}
}