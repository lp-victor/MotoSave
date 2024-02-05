/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motosave.ImplementacionesDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import motosave.DAO.ComercialDAO;
import motosave.DATA.Encriptador;
import motosave.Modelos.Comercial;

import java.util.ArrayList;
import java.util.List;

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
            // Como no se puede revertir el hash, tienes que encriptar de nuevo para comprobar si los hashes son iguales
            query.setParameter("contraseña", Encriptador.encriptarContraseña(contrasena));

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

    @Override
    public void anadirComercial(EntityManager em, Comercial comercial) {
        try {
            em.getTransaction().begin();
            em.persist(comercial);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificarComercial(EntityManager em, Comercial comercial) {
        try {
            em.getTransaction().begin();
            Comercial comercialExistente = em.find(Comercial.class, comercial.getId_comercial());
            if (comercialExistente != null) {
                comercialExistente.setUsuario(comercial.getUsuario());
                comercialExistente.setNIF(comercial.getNIF());
                comercialExistente.setNombre(comercial.getNombre());
                comercialExistente.setApellido(comercial.getApellido());
                comercialExistente.setSalario(comercial.getSalario());
                comercialExistente.setConcesionario(comercial.getConcesionario());
                em.merge(comercialExistente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarComercial(EntityManager em, Comercial comercial) {
        try {
            em.getTransaction().begin();
            Comercial comercialABorrar = em.find(Comercial.class, comercial.getId_comercial());
            if (comercialABorrar != null) {
                em.remove(comercialABorrar);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Comercial> listarComerciales(EntityManager em) {
        List<Comercial> comerciales = null;
        try {
            String jpql = "SELECT c FROM Comercial c";
            Query query = em.createQuery(jpql);
            comerciales = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(comerciales);
    }
}
