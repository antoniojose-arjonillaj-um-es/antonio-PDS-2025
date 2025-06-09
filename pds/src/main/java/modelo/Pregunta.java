package modelo;

public abstract class Pregunta {
	// Atributos
	private String pregunta;
		// Constructor
	public Pregunta (String preg) {
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
	public abstract boolean comprobarRespuesta(String dato);
}
