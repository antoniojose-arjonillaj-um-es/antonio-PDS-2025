package modelo;

public class Relleno extends Pregunta{
	// Atributo
	public String enunciado;
	
	// Constructor
	public Relleno(String resp, String enun) {
		super(resp);
		this.enunciado=enun;
	}

	@Override
	public boolean comprobarRespuesta(String dato) {
		return false;
	}

}
