package umu.pds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Usuario;
import persistencia.RepoUsuarios;

public class RepositorioTest {

	private RepoUsuarios repo;

	@BeforeEach
	public void setup() {
		repo = RepoUsuarios.getInstancia();
	}

	@AfterAll
	public static void cleanup() {
		RepoUsuarios.getInstancia().cerrar();
	}

	@Test
	public void testGuardarYRecuperarUsuario() {
		Usuario u = new Usuario();
		u.setTelefono(123456789);
		u.setNombreUs("TestUser");

		repo.guardarUsuario(u);

		Usuario obtenido = repo.getUsuarioPorTelefono(123456789);
		assertNotNull(obtenido);
		assertEquals("TestUser", obtenido.getNombreUs());
	}

	@Test
	public void testExisteUsuario() {
		Usuario u = new Usuario();
		u.setTelefono(987654321);
		u.setNombreUs("OtroUsuario");

		repo.guardarUsuario(u);

		assertTrue(repo.existeUsuario(987654321));
		assertFalse(repo.existeUsuario(111111111));
	}
}