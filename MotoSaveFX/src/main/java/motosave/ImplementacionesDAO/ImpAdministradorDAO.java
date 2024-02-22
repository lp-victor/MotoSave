/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import motosave.DAO.AdminstradorDAO;
import motosave.DATA.Encriptador;
import motosave.Modelos.Administrador;

/**
 * @author MotoSave
 */
public class ImpAdministradorDAO implements AdminstradorDAO {

    /**
     * Autentica un administrador en la base de datos mediante su nombre de usuario y contraseña.
     *
     * @param em       El EntityManager utilizado para realizar la operación.
     * @param password La contraseña del administrador.
     * @param nombre   El nombre de usuario del administrador.
     * @return true si la autenticación es exitosa, false de lo contrario.
     */
    @Override
    public boolean loggearAdmin(EntityManager em, String password, String nombre) {
        try {
            // Crea una consulta JPQL para buscar un comercial por usuario y contraseña
            String jpql = "SELECT a FROM Administrador a WHERE a.nombre = :nombre AND a.contraseña = :contraseña";
            Query query = em.createQuery(jpql);
            query.setParameter("nombre", nombre);
            // Como no se puede revertir el hash, tienes que encriptar de nuevo para comprobar si los hashes son iguales
            query.setParameter("contraseña", Encriptador.encriptarContraseña(password));

            // Ejecuta la consulta y obtiene el resultado
            Administrador admin = (Administrador) query.getSingleResult();

            return admin != null;
        } catch (NoResultException e) {
            System.out.println("Usuario incorrecto");
            return false;
        }
    }

    /**
     * Crea un Administrador en la base de datos.
     *
     * @param em    El EntityManager utilizado para realizar la operación.
     * @param admin El administrador a crear.
     */
    @Override
    public void crearAdministrador(EntityManager em, Administrador admin) {
        try {
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
