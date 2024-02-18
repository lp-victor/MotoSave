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
 * Implementación de la interfaz ComercialDAO.
 * Proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * en la base de datos para la entidad Comercial.
 */
public class ImpComercialDAO implements ComercialDAO {

    /**
     * Verifica si un comercial puede iniciar sesión con el usuario y la contraseña proporcionados.
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
     * Modifica los datos de un comercial en la base de datos.
     * @param em        El EntityManager utilizado para realizar la operación.
     * @param comercial El comercial con los nuevos datos.
     */
    @Override
    public void modificarComercial(EntityManager em, Comercial comercial) {
        try {
            em.getTransaction().begin();
            if (comercial != null) {
                em.merge(comercial);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un comercial de la base de datos.
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
