/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Automovil;
import dominio.Persona;
import dominio.Placa;
import java.util.List;
import utils.ConfiguracionPaginado;

/**
 * Interaz que define los metodos necesarios para realizar operaciones
 * relacionadas a placas con la base de datos
 *
 * @author Angel
 */
public interface IPlacaDAO {

    /**
     * Metodo que genera una nueva placa para un automovil
     *
     * @return la placa generada
     */
    public String generarPlaca();

    /**
     * Metodo que valida la placa segun su formato
     *
     * @param placa que se va a validar
     * @return la placa si es valida o no
     */
    public boolean validarPlaca(String placa);

    /**
     * Metodo que inserta las placas a un nuevo automovil para una persona
     *
     * @param persona a a la que se le asignara el automovil
     * @param automovil que se va a registrar
     * @param placaI la placa del automovil
     * @param costo de las placas para el automovil nuevo
     */
    public void insertarTramitePlacasNuevo(Persona persona, Automovil automovil, String placaI, Double costo);

    /**
     * Metodo que valida si la persona tiene un automovil asociado con las
     * placas registradas
     *
     * @param persona el propietario del auto
     * @return si la persona tiene un autmovil registrado
     */
    public boolean validaPersonaPlacas(Persona persona);

    /**
     * Metodo que busca los autmoviles registrados mediante su placa
     *
     * @param placa del autmovil a buscar
     * @param persona el propietario del automovil
     * @return la lista de los autos encontrados
     */
    public List<Automovil> buscarPlacaAutomovilL(String placa, Persona persona);

    /**
     * Metodo que busca la placa de un automovil que esten asociadas con el
     * propietario
     *
     * @param placa del automovil a buscar
     * @param persona que es propietario del automovil
     * @return el automovil encontrado
     */
    public Automovil buscarPlacaAutomovil(String placa, Persona persona);

    /**
     * Metodo que se encarga de insertar un nuevo tramite de placas al automovil
     * usado
     *
     * @param persona que representa el propietario del automovil
     * @param automovil que este registrado en el tramite
     * @param placaI que representa la nueva a registrar
     * @param costo el costo del tramite
     */
    public void insertarTramitePlacasUsado(Persona persona, Automovil automovil, String placaI, Double costo);

    /**
     *
     * Este método realiza una consulta de placas en base a los parámetros
     * proporcionados.
     *
     * @param configPaginado Objeto de tipo ConfiguracionPaginado que contiene
     * información de paginación y ordenamiento para la consulta.
     * @param persona Objeto de tipo Persona que contiene información de la
     * persona para la que se desea realizar la consulta.
     * @return Lista de objetos Placa que cumplen con los parámetros de consulta
     * proporcionados.
     */
    public List<Placa> consultaPlacas(ConfiguracionPaginado configPaginado, Persona persona);

    /**
     * Metodo que deasactiva las placas anteriores del automovil
     *
     * @param automovil del cual se van a desctivar las placas
     * @param persona el propietario del automovil
     */
    public void desactivarPlacasAnteriores(Automovil automovil, Persona persona);
}
