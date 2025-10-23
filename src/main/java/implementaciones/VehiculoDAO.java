/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import dominio.Automovil;
import dominio.Persona;
import interfaces.IVehiculoDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.ConfiguracionPaginado;

/**
 * Clase que se encarga de la de las operaciones del vehiculo integrando la
 * interfaz IVehiculoDAO
 *
 * @author Angel
 */
public class VehiculoDAO implements IVehiculoDAO {

    /**
     * Objeto que se utiliza para crear instancias de entity manager y realiza
     * las operacciones de persistencia en la base de datos
     */
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ConexionPU");
    EntityManager em = emFactory.createEntityManager();

    /**
     * Metodo que registra un vehiculo de la persona y toma como entrada un
     * objeto de automovil y un objeto de persona y registra el objeto de
     * automvil en la base de datos, estableciendo la persona como su
     * propietario
     *
     * @param auto a registrar
     * @param persona el propietario del vehiculo a registrar
     */
    @Override
    public void registrarVehiculoPersona(Automovil auto, Persona persona) {
        try {
            em.getTransaction().begin();
            auto.setPersona(persona);
            em.persist(auto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    /**
     * Metodo que consulta a los vehiculos de los cuales las personas son
     * propietarios
     *
     * @param configPaginado objeto que limita la cantidad de resultados que se
     * devuelven por consulta
     * @param persona que es el propietario del vehiculo
     * @return la lista con las personas y sus vehiculos, en caso de no existir
     * regresa una lista vacia
     */
    @Override
    public List<Automovil> consultaVehiculos(ConfiguracionPaginado configPaginado, Persona persona) {
        try {
            TypedQuery<Automovil> query = em.createQuery(
                    "select a from Automovil a "
                    + "where a.persona = :persona",
                    Automovil.class);
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
