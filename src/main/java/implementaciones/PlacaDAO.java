/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import dominio.Automovil;
import dominio.Persona;
import dominio.Placa;
import dominio.TipoPlaca;
import interfaces.IPlacaDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.RandomStringUtils;
import utils.ConfiguracionPaginado;

/**
 *
 * @author Angel
 */
public class PlacaDAO implements IPlacaDAO {

    /**
     * Objeto que se utiliza para crear instancias de entity manager y realiza
     * las operacciones de persistencia en la base de datos
     */
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ConexionPU");
    EntityManager em = emFactory.createEntityManager();

    /**
     * Metodo que hace la opercion de generar la placa de los automoviles
     *
     * @return la placa generada
     */
    @Override
    public String generarPlaca() {
        String placa = null;
        boolean placaExistente = true;
        while (placaExistente == true) {
            String caracteres = RandomStringUtils.randomAlphabetic(3).toUpperCase();
            String numeros = RandomStringUtils.randomNumeric(3);
            placa = caracteres + "-" + numeros;
            boolean placaIgual = this.validarPlaca(placa);
            if (placaIgual == false) {
                placaExistente = false;
            }
        }
        return placa;
    }

    /**
     * Metodo que se encarga de validar la placa
     *
     * @param placa que se va a validar
     * @return placa validada
     */
    @Override
    public boolean validarPlaca(String placa) {
        boolean valida = false;
        try {
            TypedQuery<Long> query = em.createQuery("select count(p) from Placa p where p.placa = :placa", Long.class);
            query.setParameter("placa", placa);
            valida = query.getSingleResult() > 0;
        } catch (Exception e) {
            e.getMessage();
        }
        return valida;
    }

    /**
     * Metodo que hacer el tramite de las placas nuevas al automovil
     *
     * @param persona el propietario que realiza el tramite
     * @param automovil al cual se le insertaran las placas
     * @param placaI nueva para el automovil
     * @param costo del tramite a realizar
     */
    @Override
    public void insertarTramitePlacasNuevo(Persona persona, Automovil automovil, String placaI, Double costo) {
        try {
            Placa placa = new Placa();
            placa.setPersona(persona);
            placa.setFechaEmision(Calendar.getInstance());
            placa.setFechaRecepcion(Calendar.getInstance());
            placa.setAutomovil(automovil);
            placa.setCosto(costo);
            placa.setEstado(TipoPlaca.Activa);
            placa.setPlaca(placaI);
            em.getTransaction().begin();
            em.persist(placa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    /**
     * Metodo que deasactiva las placas anteriores del automovil
     *
     * @param automovil del cual se van a desctivar las placas
     * @param persona el propietario del automovil
     */
    public void desactivarPlacasAnteriores(Automovil automovil, Persona persona) {
        try {
            em.getTransaction().begin();
            TypedQuery<Placa> query = em.createQuery(
                    "select p from Placa p "
                    + "where p.automovil = :automovil "
                    + "and p.persona = :persona "
                    + "and p.estado = :estado",
                    Placa.class);
            query.setParameter("automovil", automovil);
            query.setParameter("persona", persona);
            query.setParameter("estado", TipoPlaca.Activa);
            List<Placa> placasActivas = query.getResultList();
            for (Placa placa : placasActivas) {
                placa.setEstado(TipoPlaca.Desactivada);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    /**
     * Metodo que hace el tramite de insetar las placas al automovil usado
     *
     * @param persona el propietario del automovil
     * @param automovil usado del cual se le insertaran las placas
     * @param placaI las placas que se van a insertar
     * @param costo del tramite a realizar
     */
    public void insertarTramitePlacasUsado(Persona persona, Automovil automovil, String placaI, Double costo) {
        try {
            Placa placa = new Placa();
            placa.setPersona(persona);
            placa.setFechaEmision(Calendar.getInstance());
            placa.setFechaRecepcion(Calendar.getInstance());
            placa.setAutomovil(automovil);
            this.desactivarPlacasAnteriores(automovil, persona);
            placa.setCosto(costo);
            placa.setEstado(TipoPlaca.Activa);
            placa.setPlaca(placaI);
            em.getTransaction().begin();
            em.persist(placa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    /**
     * Metodo que se encarga de validar las placas del autmovil de la persona
     *
     * @param persona que desea validar las plcas
     * @return valida las placas del autmovil de la persona
     */
    public boolean validaPersonaPlacas(Persona persona) {
        boolean valida = false;
        try {
            TypedQuery<Long> query = em.createQuery(""
                    + "select count(a) from Automovil a "
                    + "inner join a.placa p "
                    + "where a.persona = :persona", Long.class);
            query.setParameter("persona", persona);
            valida = query.getSingleResult() > 0;
        } catch (Exception e) {
            e.getMessage();
        }
        return valida;
    }

    /**
     * Metodo que busca en forma de lista las placas del automovil
     *
     * @param placa del autmovil que se va a buscar
     * @param persona el propietario del autmovil que tiene las placas a buscar
     * @return las placas del automovil encontradas
     */
    public List<Automovil> buscarPlacaAutomovilL(String placa, Persona persona) {
        try {
            TypedQuery<Automovil> query = em.createQuery(
                    "select a from Automovil a "
                    + "inner join a.placa p "
                    + "where p.placa = :placa and a.persona = :persona and p.estado = :estado",
                    Automovil.class);
            query.setParameter("placa", placa);
            query.setParameter("persona", persona);
            query.setParameter("estado", TipoPlaca.Activa);
            return query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * Metodo automovil que busca las placas del automovil medianye las placas y
     * el propietario
     *
     * @param placa del automovil que van a buscar
     * @param persona que es el propietario del automovil
     * @return las placas
     */
    @Override
    public Automovil buscarPlacaAutomovil(String placa, Persona persona) {
        try {
            TypedQuery<Automovil> query = em.createQuery(
                    "select a from Automovil a "
                    + "inner join a.placa p "
                    + "where p.placa = :placa and a.persona = :persona and p.estado = :estado",
                    Automovil.class);
            query.setParameter("placa", placa);
            query.setParameter("persona", persona);
            query.setParameter("estado", TipoPlaca.Activa);
            return query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * Metodo que busca en forma de lista realiza la consulta de placas
     * asociadas a una persona
     *
     * @param configPaginado de la paginacion de la consulta
     * @param persona la cual se busca la consulta
     * @return lista de placas asociadas con la persona
     */
    public List<Placa> consultaPlacas(ConfiguracionPaginado configPaginado, Persona persona) {
        try {
            TypedQuery<Placa> query = em.createQuery(
                    "select p from Placa p "
                    + "where p.persona = :persona",
                    Placa.class);
            query.setParameter("persona", persona);
            query.setFirstResult(configPaginado.getElementosASaltar());
            query.setMaxResults(configPaginado.getElementosPagina());
            return query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
