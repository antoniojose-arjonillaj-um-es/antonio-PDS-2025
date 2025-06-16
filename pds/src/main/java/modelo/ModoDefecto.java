package modelo;

import java.util.List;

public class ModoDefecto implements ModoCurso {
	
	@Override
	public void prepararPreguntas(List<Pregunta> preguntas) {
		// No hacer nada
	}

	@Override
	public boolean usaTemporizador() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Modalidad defecto";
	}
}
