package umu.pds;

import java.util.List;

import modelo.Curso;
import modelo.Pregunta;
import modelo.Usuario;
import vistas.VentanaLogin;

/**
 * Hello world!
 *
 */
public class Controlador 
{
	
	
	// Variables globales
    public static final int ACIERTO = 0;
	public static final int ERROR_TLF = 1;
	public static final int ERROR_NOREPE = 2;

	public static final int NOMB = 0;
	public static final int TELF = 1;
	public static final int CONT = 2;
	public static final int REPE = 3;
	public static final int IMAG = 4;

	public static final String DEFECTO = "Defecto";
	public static final String ALEATORIO = "Aleatorio";
	public static final String CONTRARRELOJ = "Contrarreloj";
	
	
	// Atributos
	private static Controlador instancia = null;
	
	// Constructor
	public Controlador() {
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

	public void iniciarCurso(Usuario usuario, Curso curso) {
		usuario.comenzarCurso();
		curso.iniciar();
	}
	
	public String terminarCurso(Curso curso) {
		return curso.terminar();
	}

	public void reiniciarCurso(Usuario usuario, Curso curso) {
		curso.reiniciarCurso();
		iniciarCurso(usuario, curso);
	}
	
	public void corregirPregunta(Curso curso, Pregunta pregunta, String respuesta) {
		curso.corregirPregunta(pregunta, respuesta);
	}

	
}
