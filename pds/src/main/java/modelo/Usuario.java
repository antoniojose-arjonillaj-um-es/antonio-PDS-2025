package modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {
	// Constantes
	private static final int MAX_TICKETS = 10; // Máximo tickets por usuario
	private static final int INICIO = 0; // Defecto
	private static final double INTERVALO = 2.4; // Intervalo mínimo para adquirir ticket (horas)

	// Atributos
	@Id
	@GeneratedValue
	private long id;

	private String nombreUs;
	private String contrasena;
	private String imagen;
	private int telefono;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Curso> cursos;

	private int tickets; // Número de cursos que se pueden hacer al día
	private double tiempoUso; // Tiempo de uso de la aplicación (en horas)
	private int mejorRacha;
	private int rachaActual;

	private LocalDateTime ultimaSes; // Fecha de ultima sesión
	private LocalDateTime inicioSes; // Fecha inicio sesión actual

	// Constructor
	public Usuario() {
	}

	public Usuario(String nombreUs, String contrasena, String imagen, int telefono) {
		this.nombreUs = nombreUs;
		this.contrasena = contrasena;
		this.imagen = imagen;
		this.telefono = telefono;

		this.cursos = new ArrayList<Curso>();
		this.tickets = MAX_TICKETS;
		this.tiempoUso = INICIO;
		this.mejorRacha = INICIO;
		this.rachaActual = INICIO;

		this.ultimaSes = null;
		this.inicioSes = null;

	}

	// Getters
	public String getNombreUs() {
		return nombreUs;
	}

	public String getContrasena() {
		return contrasena;
	}

	public String getImagen() {
		return imagen;
	}

	public int getTelefono() {
		return telefono;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public int getTickets() {
		return tickets;
	}

	// Propiedad calculada para mostrar actualizacion de horas cada
	// vez que se hace un curso
	public double getTiempoUso() {
		double aux = tiempoUso;
		Duration duracion = Duration.between(inicioSes, LocalDateTime.now());
		aux += duracion.toMinutes() / 60.0;
		return aux;
	}

	public int getMejorRacha() {
		return mejorRacha;
	}

	public int getRachaActual() {
		return rachaActual;
	}

	public LocalDateTime getUltimaSes() {
		return ultimaSes;
	}

	public LocalDateTime getInicioSes() {
		return inicioSes;
	}

	// Setters
	public void setNombreUs(String nombreUs) {
		this.nombreUs = nombreUs;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public void setTiempoUso(double tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	public void setMejorRacha(int mejorRacha) {
		this.mejorRacha = mejorRacha;
	}

	public void setRachaActual(int rachaActual) {
		this.rachaActual = rachaActual;
	}

	public void setUltimaSes(LocalDateTime ultimaSes) {
		this.ultimaSes = ultimaSes;
	}

	public void setInicioSes(LocalDateTime inicioSes) {
		this.inicioSes = inicioSes;
	}

	// Métodos de clase
	public boolean comprobarContrasena(String contrasena) {
		return this.contrasena.equals(contrasena);
	}

	public void actualizarSesion() {
		this.ultimaSes = this.inicioSes;
		this.inicioSes = null;
	}

	public int calcularTickets() {
		this.inicioSes = LocalDateTime.now();
		if (ultimaSes == null) {
			tickets = MAX_TICKETS;
			return MAX_TICKETS;
		}
		Duration duracion = Duration.between(ultimaSes, inicioSes);
		int posibles = (int) (duracion.toHours() / INTERVALO);
		tickets = Math.min(MAX_TICKETS, tickets + posibles);
		return tickets;
	}

	public int calcularRacha() {
		if (ultimaSes == null)
			return INICIO;

		LocalDate diaUltima = ultimaSes.toLocalDate();
		LocalDate diaActual = inicioSes.toLocalDate();

		if (diaUltima.plusDays(1).equals(diaActual)) {
			rachaActual++;
		} else {
			rachaActual = 0;
		}

		if (rachaActual > mejorRacha) {
			mejorRacha = rachaActual;
		}

		return rachaActual;
	}

	public double actualizarTiempoUso() {
		Duration duracion = Duration.between(inicioSes, LocalDateTime.now());
		double horasDecimal = duracion.toMinutes() / 60.0;
		tiempoUso += horasDecimal;
		return tiempoUso;
	}

	public void anadirCurso(Curso curso) {
		String nombreBase = curso.getNombre();
		// Filtrar los cursos que tienen el mismo nombre base o el mismo con sufijo "-
		// Copia N"
		long count = cursos.stream().filter(c -> {
			String n = c.getNombre();
			return n.equals(nombreBase) || n.matches(Pattern.quote(nombreBase) + " - Copia \\d+");
		}).count();

		if (count > 0) {
			curso.setNombre(nombreBase + " - Copia " + count);
		}

		cursos.add(curso);
	}

	public boolean hayCursosActivos() {
		return cursos.stream().anyMatch(curso -> curso.getEstado() == Estado.EN_PROCESO);
	}

	public void comenzarCurso() {
		tickets--;
	}
}
