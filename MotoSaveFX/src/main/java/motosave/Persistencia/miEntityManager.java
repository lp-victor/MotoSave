package motosave.Persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class miEntityManager {

    // Patron 'Singleton'
    private static EntityManager entityManager = null; // Instancia única

    private miEntityManager() {} // Creamos el constructor privado para que no se pueda llamar desde fuera.

    // Este metodo es la unica forma de llamar a la instancia de miEntityManager
    // De esta forma nos aseguramos que tenemos una instancia única y no la repetimos
    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            synchronized (miEntityManager.class) {
                if (entityManager == null) {
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
                    entityManager = emf.createEntityManager();
                }
            }
        }
        return entityManager;
    }
}
