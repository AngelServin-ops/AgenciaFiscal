/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Automovil;
import dominio.Persona;
import java.util.List;
import utils.ConfiguracionPaginado;

/**
 * Interfaz que maneja las operaciones realizadas de los Vehiculos
 *
 * @author Angel
 */
public interface IVehiculoDAO {

    /**
     * Metodo que se encarga de registrar el vehiculo a la persona
     *
     * @param auto que se va a registrar
     * @param persona el propietario que quiere registrar el vehiculo
     */
    public void registrarVehiculoPersona(Automovil auto, Persona persona);

    /**
     * Este método realiza una consulta de vehículos en base a los parámetros
     * proporcionados.
     *
     * @param configPaginado Objeto de tipo ConfiguracionPaginado que contiene
     * información de paginación y ordenamiento para la consulta.
     * @param persona Objeto de tipo Persona que contiene información de la
     * persona para la que se desea realizar la consulta.
     * @return Lista de objetos Automovil que cumplen con los parámetros de
     * consulta proporcionados.
     *
     */
    public List<Automovil> consultaVehiculos(ConfiguracionPaginado configPaginado, Persona persona);
}
