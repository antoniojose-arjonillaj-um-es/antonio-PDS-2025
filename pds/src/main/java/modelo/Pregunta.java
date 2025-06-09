package modelo;

public abstract class Pregunta {
	// Atributos
	private String respuesta;
	private Tipo tipo;
	
	// Constructor
	public Pregunta (String resp, Tipo tipo) {
		this.respuesta = resp;
		this.tipo = tipo;
	}

	// Getters
	public String getRespuesta() {
		return respuesta;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	// Setters
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	// Métodos de clase
	public abstract boolean comprobarRespuesta(String dato);
}
