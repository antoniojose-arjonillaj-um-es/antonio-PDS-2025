package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Curso {
	// Atributos
	private String nombre; // Nombre curso
	private List<Pregunta> preguntas; // Lista de preguntas
	private List<Boolean> correcciones; // Estado de respuestas dadas
	private int ultima; // Ultima pregunta consultada
	private Estado estado; // Estado del curso

	// Constructor
	public Curso(String nombre, List<Pregunta> preguntas) {
		this.nombre = nombre;
		this.preguntas = preguntas;
		this.estado = Estado.SIN_EMPEZAR;
		this.ultima = 0;
		this.correcciones = new ArrayList<>(Collections.nCopies(preguntas.size(), null));
	}

	// Getters
	public String getNombre() {
		return nombre;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public int getNumPreguntas() {
		return preguntas.size();
	}

	public List<Boolean> getCorreccion() {
		return correcciones;
	}

	public int getUltima() {
		return ultima;
	}

	public Estado getEstado() {
		return estado;
	}

	public int getCorrectas() {
		int correctas = 0;
		for (Boolean resultado : correcciones) {
			if (resultado && resultado != null) {
				correctas++;
			}
		}
		return correctas;
	}

	public int getIncorrectas() {
		int incorrectas = 0;
		for (Boolean resultado : correcciones) {
			if (!resultado && resultado != null) {
				incorrectas++;
			}
		}
		return incorrectas;
	}

	// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public void setCorrecciones(List<Boolean> correcciones) {
		this.correcciones = correcciones;
	}

	public void setUltima(int ultima) {
		this.ultima = ultima;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	// Método de clase
	public void iniciar() {
		setEstado(Estado.EN_PROCESO);
	}

	// Retorna preguntas sin responder
	public List<Pregunta> getPreguntasVacias() {
		List<Pregunta> copia = new ArrayList<>();
		for (int i = 0; i < preguntas.size(); i++) {
			if (correcciones.get(i) == null)
				copia.add(preguntas.get(i));
		}
		return copia;
	}
	
	public void corregirPregunta(Pregunta pregunta, String respuesta) {
		boolean resultado = pregunta.corregir(respuesta);
		int indice = IntStream.range(0, preguntas.size())
			    .filter(i -> preguntas.get(i).equals(pregunta))
			    .findFirst()
			    .orElse(-1);
		correcciones.set(indice, resultado);
		System.out.println(resultado);
	}

	public void siguientePreg(boolean resultado) {
		correcciones.set(ultima, resultado);
		if (ultima < getNumPreguntas()) {
			ultima++;
			estado = Estado.EN_PROCESO;
		}
		ultima = -1;
		estado = Estado.FINALIZADO;
	}

	public void reiniciarCurso() {
		this.estado = Estado.SIN_EMPEZAR;
		this.ultima = 0;
		this.correcciones = new ArrayList<>(Collections.nCopies(preguntas.size(), null));
	}

	@Override
	public String toString() {
		return nombre;
	}
}
