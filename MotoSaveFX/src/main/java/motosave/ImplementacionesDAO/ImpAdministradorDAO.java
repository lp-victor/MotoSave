/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import motosave.DAO.AdminstradorDAO;
import motosave.Modelos.Administrador;
import motosave.Modelos.Comercial;

/**
 *
 * @author victo
 */
public class ImpAdministradorDAO implements AdminstradorDAO {

    @Override
    public boolean loggearAdmin(EntityManager em, String password, String usuario) {
        try {
            // Crea una consulta JPQL para buscar un comercial por usuario y contraseña
            String jpql = "SELECT a FROM Administrador a WHERE a.usuario = :usuario AND a.contraseña = :contraseña";
            Query query = em.createQuery(jpql);
            query.setParameter("usuario", usuario);
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
        } finally {
            em.close();
        }
    }

}
