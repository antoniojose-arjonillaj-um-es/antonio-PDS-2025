package umu.pds;

import java.util.List;

import modelo.Usuario;
import vistas.VentanaLogin;

/**
 * Hello world!
 *
 */
public class Controlador 
{
	// Variables de clase
    public static final int ACIERTO = 0;
	public static final int ERROR_TLF = 1;
	public static final int ERROR_NOREPE = 2;

	// Atributos
	private static Controlador instancia = null;
	private RepositorioUsuarios repositorioUs;
	
	// Constructor
	public Controlador() {
		repositorioUs = RepositorioUsuarios.getInstancia();
	}
	
	// Métodos de clase
	public static Controlador getInstancia() {
		if(instancia==null) 
			instancia = new Controlador();
		return instancia;
	}

	public Usuario aceptarLogin(String nombreUs, String contraseña) {
		// TODO Auto-generated method stub
		return null;
	}

	public int registrarUsuario(List<String> datos) {
		// TODO Auto-generated method stub
		return 0;
	}
}
