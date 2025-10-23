/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Persona;
import dominio.TipoTramite;
import dominio.Tramite;
import java.util.Date;
import java.util.List;
import utils.ConfiguracionPaginado;

/**
 * Interfaz que busca los tramites de una persona
 *
 * @author Angel
 */
public interface ITramiteDAO {

    /**
     * Metodo que busca los tramites de una persona en un rango de fechas y con
     * tipo de tramite especificado
     *
     * @param configPaginado objeto que contiene la configuración de paginado a
     * aplicar en la búsqueda.
     * @param persona objeto cuyos tramites se buscaram
     * @param tipo objeto de tramite a espeficiar al momento de buscar
     * @param fechaDesde objeto que indica la fecha inicio del rango de busqueda
     * @param fechaHasta objeto que indica la fecha final donde del rango de
     * busqueda
     * @return Una lista de objetos de la clase Tramite que coinciden con los
     * parámetros especificados en la búsqueda
     */
    public List<Tramite> buscarTramites(ConfiguracionPaginado configPaginado, Persona persona, TipoTramite tipo, Date fechaDesde, Date fechaHasta);
}
