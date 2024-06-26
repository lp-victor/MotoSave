/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import motosave.DAO.ComercialDAO;
import motosave.DATA.ComercialLoggeado;
import motosave.DATA.Encriptador;
import motosave.Modelos.Comercial;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MotoSave
 */
public class ImpComercialDAO implements ComercialDAO {

    /**
     * Verifica si un comercial puede iniciar sesión con el usuario y la contraseña proporcionados.
     *
     * @param em         El EntityManager utilizado para realizar la operación.
     * @param contrasena La contraseña del comercial.
     * @param usuario    El nombre de usuario del comercial.
     * @return true si el comercial puede iniciar sesión, false de lo contrario.
     */
    @Override
    public boolean loggearComercial(EntityManager em, String contrasena, String usuario) {
        try {
            // Crea una consulta JPQL para buscar un comercial por usuario y contraseña
            String jpql = "SELECT c FROM Comercial c WHERE c.usuario = :usuario AND c.contraseña = :contraseña";
            Query query = em.createQuery(jpql);
            query.setParameter("usuario", usuario);
            // Como no se puede revertir el hash, tienes que encriptar de nuevo para comprobar si los hashes son iguales
            query.setParameter("contraseña", Encriptador.encriptarContraseña(contrasena));

            // Ejecuta la consulta y obtiene el resultado
            Comercial comercial = (Comercial) query.getSingleResult();

            if (comercial != null) {
                ComercialLoggeado.setComercialLoggeado(comercial);
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return false;
        }
    }

    /**
     * Agrega un nuevo comercial a la base de datos.
     *
     * @param em        El EntityManager utilizado para realizar la operación.
     * @param comercial El comercial que se va a agregar.
     */
    @Override
    public void anadirComercial(EntityManager em, Comercial comercial) {
        try {
            em.getTransaction().begin();
            em.persist(comercial);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca un usuario comercial en la base de datos.
     *
     * @param em       El EntityManager utilizado para realizar la operación.
     * @param usuario_e El nombre de usuario del comercial a buscar.
     * @return true si se encuentra el usuario, false de lo contrario.
     */
    @Override
    public boolean buscarUsuarioComercial(EntityManager em, String usuario_e) {
        try {
            String jpql = "SELECT c FROM Comercial c WHERE c.usuario = :usuario";
            Query query = em.createQuery(jpql)
                    .setParameter("usuario", usuario_e);
            Comercial resultado = (Comercial) query.getSingleResult();
            return resultado != null;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca un NIF de comercial en la base de datos.
     *
     * @param em    El EntityManager utilizado para realizar la operación.
     * @param NIF_e El NIF del comercial a buscar.
     * @return true si se encuentra el NIF, false de lo contrario.
     */
    @Override
    public boolean buscarNIFComercial(EntityManager em, String NIF_e) {
        try {
            String jpql = "SELECT c FROM Comercial c WHERE c.NIF = :NIF";
            Query query = em.createQuery(jpql)
                    .setParameter("NIF", NIF_e);
            Comercial resultado = (Comercial) query.getSingleResult();
            return resultado != null;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un comercial de la base de datos.
     *
     * @param em        El EntityManager utilizado para realizar la operación.
     * @param comercial El comercial que se va a eliminar.
     */
    @Override
    public void eliminarComercial(EntityManager em, Comercial comercial) {
        try {
            em.getTransaction().begin();
            if (comercial != null) {
                em.remove(comercial);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todos los comerciales almacenados en la base de datos.
     *
     * @param em El EntityManager utilizado para realizar la operación.
     * @return Una lista de todos los comerciales almacenados.
     */
    @Override
    public ArrayList<Comercial> listarComerciales(EntityManager em) {
        try {
            String jpql = "FROM Comercial";
            Query query = em.createQuery(jpql);
            List<Comercial> comerciales = query.getResultList();
            return new ArrayList<>(comerciales);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
