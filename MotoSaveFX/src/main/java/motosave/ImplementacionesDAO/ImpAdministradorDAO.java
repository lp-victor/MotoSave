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
import motosave.Modelos.Comercial;

/**
 *
 * @author victo
 */
public class ImpAdministradorDAO implements AdminstradorDAO {

    @Override
    public boolean loggearAdmin(EntityManager em, String password, String nombre) {
        try {
            // Crea una consulta JPQL para buscar un comercial por usuario y contraseña
            String jpql = "SELECT a FROM Administrador a WHERE a.nombre = :nombre AND a.contraseña = :contraseña";
            Query query = em.createQuery(jpql);
            query.setParameter("nombre", nombre);
            // Como no se puede revertir el hash, tienes que encriptar de nuevo para comprobar si los hashes son iguales
            //query.setParameter("contraseña", Encriptador.encriptarContraseña(password));
            query.setParameter("contraseña", password);

            // Ejecuta la consulta y obtiene el resultado
            Administrador admin = (Administrador) query.getSingleResult();

            if ( admin != null) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public void crearAdministrador(EntityManager em, Administrador admin) {
        try {
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
