package modelo;

import jakarta.persistence.*;

@Entity
public class Traduccion extends Pregunta {

	// Atributos
	private String respuesta;

	// Constructor
	public Traduccion() {
		super("");
	}

	public Traduccion(String preg, String resp) {
		super(preg);
		this.respuesta = resp;
	}

	// Getters
	public String getRespuesta() {
		return respuesta;
	}

	// Setters
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	// Métodos de clase
	@Override
	public boolean corregir(String dato) {
		return respuesta.equals(dato);
	}

}
