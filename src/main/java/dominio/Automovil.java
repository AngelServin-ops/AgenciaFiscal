/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Esta es la clase Automovil representa un tipo de Vehiculo que es un automovil
 *
 * @author Angel
 */
@Entity
@PrimaryKeyJoinColumn(name = "id_vehiculo_automovil")
@Table(name = "vehiculo_automovil")
public class Automovil extends Vehiculo {

    /**
     * El id es el indentificador unico de cada automovil
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_automovil", nullable = false)
    private Long id;
    /**
     * Mapeo de la lista de placas que estan asociadas a este automovil
     */
    @OneToMany(mappedBy = "automovil", targetEntity = Placa.class)
    private List<Placa> placa;

    /**
     * Metodo que obtiene el id unico del automovil
     *
     * @return el id unico del automovil
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Metodo que se encarga de establecer el id de automovil
     *
     * @param id como el nuevo identificador para el automovil
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que obtiene la lista de placas que estan asociadas a automovil
     *
     * @return lista de placas
     */
    public List<Placa> getPlaca() {
        return placa;
    }

    /**
     * Metodo que establece la placas que estan asociadas a automovil
     *
     * @param placa la nueva lista de placas
     */
    public void setPlaca(List<Placa> placa) {
        this.placa = placa;
    }

    /**
     * Metodo que obtiene todos los atributos de la clase automovil
     *
     * @return el automovil con sus atributos establecidos en forma de cadena
     */
    @Override
    public String toString() {
        return "Automovil{" + "id=" + id + ", placa=" + placa + '}';
    }

}
