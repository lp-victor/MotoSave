/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import motosave.DAO.ComercialDAO;
import motosave.Modelos.Comercial;

/**
 *
 * @author victo
 */
public class ImpComercialDAO implements ComercialDAO {

    @Override
    public boolean loggearComercial(EntityManager em, String contrasena, String usuario) {
        try {
            // Crea una consulta JPQL para buscar un comercial por usuario y contraseña
            String jpql = "SELECT c FROM Comercial c WHERE c.usuario = :usuario AND c.contraseña = :contraseña";
            Query query = em.createQuery(jpql);
            query.setParameter("usuario", usuario);
            query.setParameter("contraseña", contrasena);

            // Ejecuta la consulta y obtiene el resultado
            Comercial comercial = (Comercial) query.getSingleResult();

            if ( comercial != null) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return false;
        }
    }
}
