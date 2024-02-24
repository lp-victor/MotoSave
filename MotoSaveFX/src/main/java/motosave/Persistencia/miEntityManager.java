package motosave.Persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
/**
 * Esta clase proporciona un EntityManager utilizando el patrón SINGLETON para garantizar una única instancia en la aplicación.
 * El EntityManager se utiliza para interactuar con la capa de persistencia de la aplicación.
 *
 * @author MotoSave
 */
public class miEntityManager {

    // Instancia única de EntityManager
    private static EntityManager entityManager = null; // Instancia única

    private miEntityManager() {
    } // Constructor privado para que no se pueda llamar desde fuera de la clase.

    // Este metodo es la unica forma de llamar a la instancia de miEntityManager
    // De esta forma nos aseguramos que tenemos una instancia única y no la repetimos
    /**
     * Devuelve la instancia única de EntityManager.
     *
     * @return La instancia única de EntityManager.
     */
    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            if (entityManager == null) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
                entityManager = emf.createEntityManager();
            }
        }
        return entityManager;
    }

}
