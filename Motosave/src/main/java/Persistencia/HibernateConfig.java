
package persistencia;

import jakarta.persistence.*;

public class HibernateConfig {
    private EntityManagerFactory factory;
    private EntityManager entityManager;

    public HibernateConfig() {
        this.setFactory(Persistence.createEntityManagerFactory("persistence_ms"));
    }

    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public EntityManager getEntityManager() {
        return entityManager = new HibernateConfig().getFactory().createEntityManager();
    }
    
    public void closeConnection(){
        entityManager.close();
        factory.close();
    }
}
