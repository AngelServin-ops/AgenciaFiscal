/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import dominio.Licencia;
import dominio.Persona;
import dominio.TipoLicencia;
import interfaces.ILicenciaDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import utils.ConfiguracionPaginado;

/**
 *
 * @author Angel
 */
public class LicenciaDAO implements ILicenciaDAO {

    /**
     * Objeto que se utiliza para crear instancias de entity manager y realiza
     * las operacciones de persistencia en la base de datos
     */
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ConexionPU");
    EntityManager em = emFactory.createEntityManager();

    /**
     * Metodo para insertar un tramite del tipo licencia
     *
     * @param persona la cual hara el tramite
     * @param costo del tramite de licencia
     * @param vigencia la duracion de la vigencia en anios
     * @param estadoDiscapacidad si la persona es o no discapacitado
     */
    @Override
    public void insertarTramiteLicencia(Persona persona, Double costo, int vigencia, int estadoDiscapacidad) {
        try {
            Licencia licencia = new Licencia();
            if (estadoDiscapacidad == 1) {
                licencia.setTipoLicencia(TipoLicencia.Discapacitado);
            } else {
                licencia.setTipoLicencia(TipoLicencia.Normal);
            }
            licencia.setVigencia(vigencia);
            licencia.setCosto(costo);
            licencia.setFechaEmision(Calendar.getInstance());
            licencia.setPersona(persona);

            em.getTransaction().begin();
            em.persist(licencia);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } 
    }

    /**
     * Metodo para validar la vigencia de la licencia de una persona, en caso de
     * no existir o no escribir bien los datos lanza un mensaje indicando el
     * error
     *
     * @param rfc de la persona a verificar
     * @return la vigencia de la licencia
     */
    @Override
    public boolean validarLicenciaVigente(String rfc) {
        boolean vigencia = false;
        try {
            TypedQuery<Licencia> queryLicencia = em.createQuery("select l from Licencia l "
                    + "inner join l.persona p "
                    + "where p.rfc = :rfc "
                    + "order by l.fechaEmision desc", Licencia.class);
            queryLicencia.setParameter("rfc", rfc);
            queryLicencia.setMaxResults(1);
            Licencia licencia = queryLicencia.getSingleResult();
            Calendar fechaVencimiento = Calendar.getInstance();
            fechaVencimiento.add(Calendar.YEAR, licencia.getVigencia());
            Calendar fechaActual = Calendar.getInstance();
            if (fechaVencimiento.after(fechaActual)) {
                vigencia = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No tiene o no se encontró una licencia vigente para la persona con el RFC " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return vigencia;
    }

    /**
     * Metodo lista que realiza la consulta de licencia de una persona
     *
     * @param configPaginado la configuracion del paginado para la consulta
     * @param persona que le pertenece la licencia
     * @return la lista de licencias de la persona
     */
    @Override
    public List<Licencia> consultaLicencias(ConfiguracionPaginado configPaginado, Persona persona) {
        try {
            TypedQuery<Licencia> query = em.createQuery(
                    "select l from Licencia l "
                    + "where l.persona = :persona",
                    Licencia.class);
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
