package modelo;

import jakarta.persistence.*;

@Entity
public class Relleno extends Pregunta {
	// Atributo
	public String segundaPreg;
	public String respuesta;

	// Constructor
	public Relleno() {
		super("");
	}

	public Relleno(String preg, String seg, String resp) {
		super(preg);
		this.segundaPreg = seg;
		this.respuesta = resp;
	}

	// Getters
	public String getSegundaPreg() {
		return segundaPreg;
	}

	public String getRespuesta() {
		return respuesta;
	}

	// Setters
	public void setSegundaPreg(String segundaPreg) {
		this.segundaPreg = segundaPreg;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	// Métodos de clase
	@Override
	public boolean corregir(String dato) {
		return respuesta.equals(dato);
	}

}
