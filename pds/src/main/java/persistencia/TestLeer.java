package persistencia;

import jakarta.persistence.*;

public class TestLeer {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo");
        EntityManager em = emf.createEntityManager();

        // Supongamos que quieres leer la Persona con id = 1
        Persona p = em.find(Persona.class, 1L);

        if (p != null) {
            System.out.println("Persona encontrada: " + p.getNombre());
        } else {
            System.out.println("Persona no encontrada");
        }

        em.close();
        emf.close();
    }
}
