package umu.pds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;

class UsuarioTest {

	private Usuario usuario;

	@BeforeEach
	void setUp() {
		usuario = new Usuario("antonio", "1234", "imagen.png", 123456789);
	}

	@Test
	void testConstructorYGetters() {
		assertEquals("antonio", usuario.getNombreUs());
		assertEquals("1234", usuario.getContrasena());
		assertEquals("imagen.png", usuario.getImagen());
		assertEquals(123456789, usuario.getTelefono());
		assertEquals(10, usuario.getTickets());
		assertEquals(0, usuario.getMejorRacha());
		assertEquals(0, usuario.getRachaActual());
		assertNull(usuario.getUltimaSes());
		assertNull(usuario.getInicioSes());
		assertNotNull(usuario.getCursos());
	}

	@Test
	void testGetTiempoUsoInicial() {
		usuario.setInicioSes(LocalDateTime.now());
		double tiempo = usuario.getTiempoUso();
		assertTrue(tiempo >= 0);
	}

	@Test
	void testComprobarContrasena() {
		assertTrue(usuario.comprobarContrasena("1234"));
		assertFalse(usuario.comprobarContrasena("incorrecta"));
	}

	@Test
	void testActualizarSesion() {
		usuario.setInicioSes(LocalDateTime.now().minusHours(2));
		usuario.actualizarSesion();
		assertNotNull(usuario.getUltimaSes());
		assertNull(usuario.getInicioSes());
	}

	@Test
	void testCalcularTicketsSinUltimaSesion() {
		usuario.setUltimaSes(null);
		int tickets = usuario.calcularTickets();
		assertEquals(10, tickets);
	}

	@Test
	void testCalcularTicketsConTiempo() {
		usuario.setUltimaSes(LocalDateTime.now().minusHours(10));
		int tickets = usuario.calcularTickets();
		assertTrue(tickets <= 10);
	}

	@Test
	void testCalcularRachaContinua() {
		LocalDateTime ayer = LocalDateTime.now().minusDays(1);
		usuario.setUltimaSes(ayer);
		usuario.setInicioSes(LocalDateTime.now());
		int racha = usuario.calcularRacha();
		assertEquals(1, racha);
		assertEquals(1, usuario.getMejorRacha());
	}

	@Test
	void testCalcularRachaNoContinua() {
		usuario.setUltimaSes(LocalDateTime.now().minusDays(3));
		usuario.setInicioSes(LocalDateTime.now());
		int racha = usuario.calcularRacha();
		assertEquals(0, racha);
	}

	@Test
	void testActualizarTiempoUso() {
		usuario.setInicioSes(LocalDateTime.now().minusMinutes(120));
		usuario.setTiempoUso(0.0);
		double tiempo = usuario.actualizarTiempoUso();
		assertEquals(2.0, tiempo, 0.01); // margen de error pequeño por división
	}

	@Test
	void testAnadirCurso() {
		Curso curso = new Curso();
		usuario.anadirCurso(curso);
		assertTrue(usuario.getCursos().contains(curso));
	}

	@Test
	public void testAnadirCursoConNombreDuplicado() {
		Usuario usuario = new Usuario("usuario1", "pass", "img.png", 123456);

		// Añadimos primer curso con nombre "Curso A"
		Curso curso1 = new Curso("Curso A", new ArrayList<>());
		usuario.anadirCurso(curso1);

		// Añadimos segundo curso con el mismo nombre "Curso A"
		Curso curso2 = new Curso("Curso A", new ArrayList<>());
		usuario.anadirCurso(curso2);

		// Añadimos tercer curso con el mismo nombre "Curso A"
		Curso curso3 = new Curso("Curso A", new ArrayList<>());
		usuario.anadirCurso(curso3);

		// El primer curso mantiene su nombre original
		assertEquals("Curso A", usuario.getCursos().get(0).getNombre());

		// El segundo curso debe tener sufijo "- Copia 1"
		assertEquals("Curso A - Copia 1", usuario.getCursos().get(1).getNombre());

		// El tercer curso debe tener sufijo "- Copia 2"
		assertEquals("Curso A - Copia 2", usuario.getCursos().get(2).getNombre());
	}

	@Test
	void testHayCursosActivos() {
		Curso cursoActivo = new Curso();
		cursoActivo.setEstado(Estado.EN_PROCESO);
		usuario.anadirCurso(cursoActivo);

		assertTrue(usuario.hayCursosActivos());
	}

	@Test
	void testHayCursosNoActivos() {
		Curso curso = new Curso();
		curso.setEstado(Estado.FINALIZADO);
		usuario.anadirCurso(curso);

		assertFalse(usuario.hayCursosActivos());
	}

	@Test
	void testComenzarCurso() {
		int before = usuario.getTickets();
		usuario.comenzarCurso();
		assertEquals(before - 1, usuario.getTickets());
	}
}
