package modelo;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pregunta {
	
	// Atributos
	@Id @GeneratedValue
	private Long id;

	private String pregunta;

	// Constructor
	public Pregunta(String preg) {
		this.pregunta = preg;
	}

	// Getters
	public String getPregunta() {
		return pregunta;
	}

	// Setters
	public void setPregutna(String preg) {
		this.pregunta = preg;
	}

	// Métodos de clase
	public abstract boolean corregir(String dato);
}
