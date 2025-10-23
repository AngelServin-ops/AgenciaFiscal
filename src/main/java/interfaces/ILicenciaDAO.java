/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Licencia;
import dominio.Persona;
import java.util.List;
import utils.ConfiguracionPaginado;

/**
 * Interfaz que define los metodos que deben implementarse para realizar
 * operaciones relacionadas con las Licencias de Conducir en una aplicación
 *
 * @author Angel
 */
public interface ILicenciaDAO {

    /**
     * Metodo metodo se encarga de insertar un nuevo tramite de licencia para
     * una persona, especificando el costo, la vigencia y el estado de
     * discapacidad de la persona
     *
     * @param persona a la que se le asignara la licencia
     * @param costo del tramite de la licencia
     * @param vigencia de la licencia
     * @param estadoDiscapacidad de la persona
     */
    public void insertarTramiteLicencia(Persona persona, Double costo, int vigencia, int estadoDiscapacidad);

    /**
     * Metodo se encarga de validar si una licencia de conducir asociada a un
     * RFC especifico se encuentra vigente
     *
     * @param rfc de la persona
     * @return si indica que licencia esta vigente
     */
    public boolean validarLicenciaVigente(String rfc);

    /**
     * Este método realiza una consulta de licencias en base a los parámetros
     * proporcionados.
     *
     * @param configPaginado Objeto de tipo ConfiguracionPaginado que contiene
     * información de paginación y ordenamiento para la consulta.
     * @param persona Objeto de tipo Persona que contiene información de la
     * persona para la que se desea realizar la consulta.
     * @return Lista de objetos Licencia que cumplen con los parámetros de
     * consulta proporcionados.
     */
    public List<Licencia> consultaLicencias(ConfiguracionPaginado configPaginado, Persona persona);
}
