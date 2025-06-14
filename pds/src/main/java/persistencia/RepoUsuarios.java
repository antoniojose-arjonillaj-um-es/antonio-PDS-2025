package persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import modelo.Curso;
import modelo.Usuario;

public class RepoUsuarios {

	// Atributos
	private static RepoUsuarios instancia;

	private EntityManagerFactory emf;
	private EntityManager em;

	private Map<Integer, Usuario> usuarios;

	// Constructor
	private RepoUsuarios() {
		emf = Persistence.createEntityManagerFactory("CopialingoDB");
		em = emf.createEntityManager();

		usuarios = new HashMap<>();

		 // Recupera todos los usuarios sin fetch join para evitar error
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        List<Usuario> lista = query.getResultList();

        // Inicializamos las colecciones manualmente
        for (Usuario u : lista) {
            inicializarColecciones(u);
            usuarios.put(u.getTelefono(), u);
        }
	}

	// Patrón singleton
	public static RepoUsuarios getInstancia() {
		if (instancia == null) {
			instancia = new RepoUsuarios();
		}
		return instancia;
	}

	private void inicializarColecciones(Usuario u) {
        // Carga lista de cursos
        u.getCursos().size();

        // Para cada curso, carga lista de preguntas
        for (Curso c : u.getCursos()) {
            c.getPreguntas().size();
        }
    }
	
	// Métodos de clase
	public Map<Integer, Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuarioPorTelefono(Integer telefono) {
		return usuarios.get(telefono);
	}

	public Usuario getUsuarioPorNombre(String nombre) {
		return usuarios.values().stream().filter(u -> u.getNombreUs().equals(nombre)).findFirst().orElse(null);
	}

	public void guardarUsuario(Usuario usuario) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(usuario); // merge actualiza si ya existe, o inserta si no
			tx.commit();
			// Actualizamos mapa por si hace falta mantenerla sincronizada
			usuarios.put(usuario.getTelefono(), usuario);
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	public boolean existeUsuario(int tlf) {
		if (getUsuarioPorTelefono(tlf) != null)
			return true;
		return false;
	}

	public void cerrar() {
		if (em.isOpen())
			em.close();
		if (emf.isOpen())
			emf.close();
	}
}
