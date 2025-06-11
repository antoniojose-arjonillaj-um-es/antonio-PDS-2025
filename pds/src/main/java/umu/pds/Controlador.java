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

	public static final int NOMB = 0;
	public static final int TELF = 1;
	public static final int CONT = 2;
	public static final int REPE = 3;
	public static final int IMAG = 4;

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
		// TODO: Crear método en repositorio
		Usuario user=null;// = repositorioUs.getUsuario(nombreUs);
		if(user.comprobarContrasena(contraseña))
			return user;
		return null;
	}

	public int registrarUsuario(List<String> datos) {
		// TODO: Crear método en repositorio
//		if(repositorioUs.existeUsuario(Integer.parseInt(datos.get(TELF))))
//			return ERROR_TLF;
		
		if(!datos.get(CONT).equals(datos.get(REPE)))
			return ERROR_NOREPE;
		
		Usuario user = new Usuario(datos.get(NOMB), datos.get(CONT), datos.get(IMAG), Integer.parseInt(datos.get(TELF)));
		//TODO: Guardar en repositorio y en persistencia
		return ACIERTO;
	}
}
