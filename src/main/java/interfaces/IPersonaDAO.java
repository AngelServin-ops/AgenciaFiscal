/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Persona;
import java.util.List;
import utils.ConfiguracionPaginado;

/**
 * Interfaz que define los metodos necesarios para realizar operaciones
 * relacionadas con la entidad Persona en la base de datos
 *
 * @author Angel
 */
public interface IPersonaDAO {

    /**
     * Metodo que se encarga de insertar personas en la base de datos
     */
    public void insertarPersonas();

    /**
     * Metodo que cuenta el número total de personas en la base de datos
     *
     * @return la cantidad de personas contadas
     */
    public long contarPersonas();

    /**
     * Metodo que realiza una busqueda de personas en la base de datos con un
     * criterio de paginacion y filtros opcionales de RFC, nombre y fecha de
     * nacimiento
     *
     * @param configPaginado objeto que limita la cantidad de resultados que se
     * devuelven por consulta
     * @param rfc de la persona a buscar
     * @param nombre de la persona a buscar
     * @param ano de la persona a buscar
     * @return los datos de las personas contadas
     */
    public List<Persona> buscarPersonas(ConfiguracionPaginado configPaginado, String rfc, String nombre, String ano);

    /**
     * Metodo que busca una persona en la base de datos mediante su RFC
     *
     * @param rfc de la persona para hacer la busqueda
     * @return la persona con el rfc indicado
     */
    public Persona buscarPersonasRFC(String rfc);

    /**
     * Metodo que valida si una persona con RFC especificado existe en la base
     * de datos
     *
     * @param rfc de la persona a validar
     * @return el rfc de la persona
     */
    public boolean validarPersonaRFC(String rfc);
}
