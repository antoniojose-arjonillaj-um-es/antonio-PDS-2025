package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Curso {
	// Atributos
	private List<Pregunta> preguntas;	// Lista de preguntas
	private List<Boolean> correccion;	// Estado de respuestas dadas
	private int ultima;					// Ultima pregunta consultada
	private Estado estado;				// Estado del curso
	
	// Constructor
	public Curso(List<Pregunta> preguntas) {
		this.preguntas=preguntas;
		this.estado=Estado.SIN_EMPEZAR;
		this.ultima=0;
		this.correccion=new ArrayList<>(Collections.nCopies(preguntas.size(), null));
	}

	// Getters
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	
	public int getNumPreguntas() {
		return preguntas.size();
	}
	
	public List<Boolean> getCorreccion(){
		return correccion;
	}
	
	public int getUltima(){
		return ultima;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public int getCorrectas() {
		int correctas = 0;
		for (Boolean resultado : correccion) {
			if(resultado) {
				correctas++;
			}
		}
		return correctas;
	}
	
	// Setters
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
