package umu.pds;

public class RepositorioUsuarios {
	// Atributos
	private static RepositorioUsuarios instancia = null;
	
	// Constructor
	public RepositorioUsuarios() {
		
	}
	
	// Métodos de clase
	public static RepositorioUsuarios getInstancia() {
		if(instancia==null) 
			instancia = new RepositorioUsuarios();
		return instancia;
	}
}
