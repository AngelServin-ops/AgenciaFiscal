/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import dominio.Persona;
import dominio.TipoTramite;
import dominio.Tramite;
import interfaces.ITramiteDAO;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.ConfiguracionPaginado;

/**
 * Clase que se encarga la transaccion de los tramites, interactua con la clase
 * Tramite en la base de datos
 *
 * @author Angel
 */
public class TramiteDAO implements ITramiteDAO {

    /**
     * Objeto que se utiliza para crear instancias de entity manager y realiza
     * las operacciones de persistencia en la base de datos
     */
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ConexionPU");
    EntityManager em = emFactory.createEntityManager();

    /**
     * Metodo que recibe como parametros una configuracion de paginado de una
     * persona, un tipo de tramite, una fecha de inicio y una fecha de fin, y se
     * encarga de buscar los tramites que correspondan a los criterios de
     * busqueda especificados en la base de datos
     *
     * @param configPaginado objeto que limita la cantidad de resultados que se
     * devuelven por consulta
     * @param persona que representa los tramites a buscar
     * @param tipo de tramite a buscar
     * @param fechaDesde del periodo que se desea buscar el tramite
     * @param fechaHasta del periodo que se desea buscar el tramite
     * @return una lista de objetos del tipo tramite que se encontraron, en caso
     * de no existir devuelve una lista vacia
     */
    @Override
    public List<Tramite> buscarTramites(ConfiguracionPaginado configPaginado, Persona persona, TipoTramite tipo, Date fechaDesde, Date fechaHasta) {
        try {
            TypedQuery<Tramite> query = em.createQuery(
                    "select t from Tramite t "
                    + "where t.persona = :persona "
                    + "and t.fechaEmision between :fechaDesde and :fechaHasta and t.tipo = :tipo", Tramite.class);
            query.setParameter("persona", persona);
            query.setParameter("fechaDesde", fechaDesde);
            query.setParameter("fechaHasta", fechaHasta);
            query.setParameter("tipo", tipo);
            query.setFirstResult(configPaginado.getElementosASaltar());
            query.setMaxResults(configPaginado.getElementosPagina());
            return query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
