package modelo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({ @JsonSubTypes.Type(value = Test.class, name = "Test"),
		@JsonSubTypes.Type(value = Traduccion.class, name = "Traduccion"),
		@JsonSubTypes.Type(value = Relleno.class, name = "Relleno") })

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pregunta {

	// Atributos
	@Id
	@GeneratedValue
	private Long id;

	private String pregunta;

	// Constructor
	public Pregunta() {
	}

	public Pregunta(String preg) {
		this.pregunta = preg;
	}

	// Getters
	public String getPregunta() {
		return pregunta;
	}

	// Setters
	public void setPregunta(String preg) {
		this.pregunta = preg;
	}

	// Métodos de clase
	public abstract boolean corregir(String dato);
}
