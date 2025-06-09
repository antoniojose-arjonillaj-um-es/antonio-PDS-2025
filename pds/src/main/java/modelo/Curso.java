package modelo;

import java.util.List;

public class Curso {
	// Atributos
	private List<Pregunta> preguntas;
	
	// Constructor
	public Curso(List<Pregunta> preguntas) {
		this.preguntas=preguntas;
	}

	// Getters
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	
	public int getNumPreguntas() {
		return preguntas.size();
	}

	// Setters
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	// Métodos de clase
}
