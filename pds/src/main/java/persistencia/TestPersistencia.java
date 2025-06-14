package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestPersistencia {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Persona p = new Persona("Juan");
        em.persist(p);

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Persona guardada con ID: " + p.getId());
        System.out.println("Persona guardada con nombre: " +p.getNombre());
    }
}
