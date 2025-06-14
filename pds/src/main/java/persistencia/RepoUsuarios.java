package persistencia;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import modelo.Usuario;

public class RepoUsuarios {

	// Atributos
	private static RepoUsuarios instancia;

	private EntityManagerFactory emf;
	private EntityManager em;

	private List<Usuario> usuarios;

	// Constructor
	private RepoUsuarios() {
		emf = Persistence.createEntityManagerFactory("CopialingoDB");
		em = emf.createEntityManager();

		// Recupera todos los usuarios con sus cursos
		TypedQuery<Usuario> query = em.createQuery(
				"SELECT DISTINCT u FROM Usuario u " + 
				"LEFT JOIN FETCH u.cursos c " + 
				"LEFT JOIN FETCH c.preguntas",
				Usuario.class);
		usuarios = query.getResultList();
	}

	// Patrón singleton
	public static RepoUsuarios getInstancia() {
		if (instancia == null) {
			instancia = new RepoUsuarios();
		}
		return instancia;
	}

	// Métodos de clase
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void cerrar() {
		if (em.isOpen())
			em.close();
		if (emf.isOpen())
			emf.close();
	}
}
