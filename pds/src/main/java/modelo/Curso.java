package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Curso {
	// Atributos
	@Id
	@GeneratedValue
	private Long id;

	private String nombre; // Nombre curso

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pregunta> preguntas; // Lista de preguntas

	@ElementCollection
	private List<Boolean> correcciones; // Estado de respuestas dadas

	private int contestadas; // Número preguntas contestadas

	@Enumerated(EnumType.STRING)
	private Estado estado; // Estado del curso

	// Constructor
	public Curso(String nombre, List<Pregunta> preguntas) {
		this.nombre = nombre;
		this.preguntas = preguntas;
		this.estado = Estado.SIN_EMPEZAR;
		this.contestadas = 0;
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

	public List<Boolean> getCorrecciones() {
		return correcciones;
	}

	public int getContestadas() {
		return contestadas;
	}

	public Estado getEstado() {
		return estado;
	}

	public int getCorrectas() {
		int correctas = 0;
		for (Boolean resultado : correcciones) {
			if (resultado != null && resultado) {
				correctas++;
			}
		}
		return correctas;
	}

	public int getIncorrectas() {
		int incorrectas = 0;
		for (Boolean resultado : correcciones) {
			if (!resultado || resultado == null) {
				incorrectas++;
			}
		}
		return incorrectas;
	}

	public String getResultados() {
		return "Correctas: " + getCorrectas() + "\nIncorrectas: " + getIncorrectas();
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

	public void setContestadas(int contestadas) {
		this.contestadas = contestadas;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	// Método de clase
	public void iniciar() {
		setEstado(Estado.EN_PROCESO);
	}

	public void pausar() {
		setEstado(Estado.PAUSADO);
	}

	public String terminar() {
		setEstado(Estado.FINALIZADO);
		return "Resultados:\n" + getResultados();
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
		int indice = IntStream.range(0, preguntas.size()).filter(i -> preguntas.get(i).equals(pregunta)).findFirst()
				.orElse(-1);
		if (indice != -1) {
			correcciones.set(indice, resultado);
			contestadas++;
		}
	}

	public void reiniciarCurso() {
		this.estado = Estado.SIN_EMPEZAR;
		this.contestadas = 0;
		this.correcciones = new ArrayList<>(Collections.nCopies(preguntas.size(), null));
	}

	@Override
	public String toString() {
		return nombre;
	}
}
