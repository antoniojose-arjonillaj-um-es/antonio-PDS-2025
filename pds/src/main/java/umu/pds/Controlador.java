package umu.pds;

import java.util.List;

import modelo.Curso;
import modelo.Pregunta;
import modelo.Usuario;

public class Controlador {

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
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	/*
	 * Métodos gestión de usuario
	 */

	public Usuario login(String nombreUs, String contraseña) {
		Usuario user = null;// TODO: persistencia obtiene usuario
		if (user.comprobarContrasena(contraseña)) {
			user.calcularRacha();
			user.calcularTickets();
			return user;
		}
		return null;
	}

	public boolean logout(Usuario usuario) {
		if (usuario.hayCursosActivos())
			return false;
		usuario.actualizarTiempoUso();
		// TODO: persistencia guarda usuario y sus cursos importados
		return true;
	}

	public int registrarUsuario(List<String> datos) {
		// TODO: persistencia recupera lista de usuarios
//		if(repositorioUs.existeUsuario(Integer.parseInt(datos.get(TELF))))
//			return ERROR_TLF;

		if (!datos.get(CONT).equals(datos.get(REPE)))
			return ERROR_NOREPE;

		Usuario user = new Usuario(datos.get(NOMB), datos.get(CONT), datos.get(IMAG),
				Integer.parseInt(datos.get(TELF)));
		// TODO: persistencia registra usuario
		return ACIERTO;
	}

	public void cambiarImagen(Usuario usuario, String nuevaUrl) {
		usuario.setImagen(nuevaUrl);
	}

	/*
	 * Métodos para la gestión del curso
	 */

	public void importarCurso() {
		// TODO: persistencia obtiene curso de archivo y lo añade a usuario
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

	public void continuarCurso(Curso curso) {
		curso.iniciar();
	}

	public void pausarCurso(Curso curso) {
		curso.pausar();
	}

	public void corregirPregunta(Curso curso, Pregunta pregunta, String respuesta) {
		curso.corregirPregunta(pregunta, respuesta);
	}
}
