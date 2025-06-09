package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Usuario {
	// Constantes
	private static final int MAX_TICKETS = 10;
	private static final int INICIO = 0;
	
	// Atributos
	private String nombreUs;
	private String contrasena;
	private String imagen;
	private List<Curso> cursos;
	
	private int tickets;	// Número de cursos que se pueden hacer al día
	private int tiempoUso; 	// Tiempo de uso de la aplicación (en horas)
	private int mejorRacha; 
	private int rachaActual;
	
	private LocalDateTime ultimaSes;	// Fecha de ultima sesión
	private LocalDateTime inicioSes;	// Fecha inicio sesión actual
	
	
	// Constructor
	public Usuario (String nombreUs, String contrasena, String imagen, List<Curso> cursos) {
		this.nombreUs=nombreUs;
		this.contrasena=contrasena;
		this.imagen=imagen;
		this.cursos=cursos;
		
		this.tickets = MAX_TICKETS;
		this.tiempoUso= INICIO;
		this.mejorRacha=INICIO;
		this.rachaActual=INICIO;
		
		this.ultimaSes=null;
		this.inicioSes=null;
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

	public List<Curso> getCursos() {
		return cursos;
	}

	public int getTickets() {
		return tickets;
	}

	public int getTiempoUso() {
		return tiempoUso;
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
	
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	public void setTickets(int tickets) {
		this.tickets = tickets;
	}
	
	public void setTiempoUso(int tiempoUso) {
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
}
