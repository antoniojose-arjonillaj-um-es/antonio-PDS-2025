package copialingo;

import java.io.File;
import java.util.List;

import modelo.Curso;
import modelo.ModoAleatorio;
import modelo.ModoContrarreloj;
import modelo.ModoContrarrelojAleatorio;
import modelo.ModoCurso;
import modelo.ModoDefecto;
import modelo.Pregunta;
import modelo.Usuario;
import persistencia.ImportadorCursosYAML;
import persistencia.RepoUsuarios;

public class Controlador {

	// Variables globales
	public static final int ACIERTO = 0;
	public static final int ERROR_TLF = 1;
	public static final int ERROR_NOREPE = 2;
	public static final int ERROR_NAME = 3;

	public static final int NOMB = 0;
	public static final int TELF = 1;
	public static final int CONT = 2;
	public static final int REPE = 3;
	public static final int IMAG = 4;

	// Atributos
	private static Controlador instancia = null;
	private RepoUsuarios repositorio;
	private ImportadorCursosYAML importadorYAML;

	// Constructor
	private Controlador() {
		this.repositorio = RepoUsuarios.getInstancia();
		this.importadorYAML = ImportadorCursosYAML.getInstancia();
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
		Usuario user;
		// Si nos pasan un teléfono
		if (nombreUs.matches("\\d+"))
			user = repositorio.getUsuarioPorTelefono(Integer.parseInt(nombreUs));
		// Si nos pasan un nombre de usuario
		else
			user = repositorio.getUsuarioPorNombre(nombreUs);

		if (user != null && user.comprobarContrasena(contraseña)) {
			user.calcularTickets();
			user.calcularRacha();
			return user;
		}
		return null;
	}

	public boolean logout(Usuario usuario) {
		if (usuario.hayCursosActivos())
			return false;
		usuario.actualizarTiempoUso();
		repositorio.guardarUsuario(usuario);
		repositorio.cerrar();
		return true;
	}

	public int registrarUsuario(List<String> datos) {
		if (repositorio.existeUsuario(Integer.parseInt(datos.get(TELF))))
			return ERROR_TLF;

		if (repositorio.getUsuarioPorNombre(datos.get(NOMB)) != null)
			return ERROR_NAME;

		if (!datos.get(CONT).equals(datos.get(REPE)))
			return ERROR_NOREPE;

		Usuario user = new Usuario(datos.get(NOMB), datos.get(CONT), datos.get(IMAG),
				Integer.parseInt(datos.get(TELF)));
		repositorio.guardarUsuario(user);
		return ACIERTO;
	}

	public void cambiarImagen(Usuario usuario, String nuevaUrl) {
		usuario.setImagen(nuevaUrl);
	}

	/*
	 * Métodos para la gestión del curso
	 */

	public boolean importarCurso(Usuario usuario, File file) throws Exception {
		Curso curso = this.importadorYAML.importarCursosFichero(file);
		if (curso != null) {
			usuario.anadirCurso(curso);
			return true;
		} else
			return false;
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

	public List<ModoCurso> obtenerModos() {
		return List.of(new ModoDefecto(), new ModoAleatorio(), new ModoContrarreloj(), new ModoContrarrelojAleatorio());
	}

	/*
	 * Funcionalidad extra
	 */

	public int compartirCurso(String id, Curso curso) {
		Usuario user;
		if (id.matches("\\d+"))
			user = repositorio.getUsuarioPorTelefono(Integer.parseInt(id));
		else
			user = repositorio.getUsuarioPorNombre(id);
		if (user == null)
			return ERROR_NAME;
		Curso copia = new Curso(curso.getNombre(), curso.getPreguntas());
		user.anadirCurso(copia);
		repositorio.guardarUsuario(user);
		return ACIERTO;
	}
}
