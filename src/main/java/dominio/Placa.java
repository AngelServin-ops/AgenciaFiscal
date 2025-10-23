/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Esta clase representa los datos de la placa asociadas con tramite
 *
 * @author Angel
 */
@Entity
@PrimaryKeyJoinColumn(name = "id_tramite_placa")
@DiscriminatorValue("Placa")
@Table(name = "tramite_placa")
public class Placa extends Tramite {

    /**
     * El id es el identificador unico de cada placa
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_placa", nullable = false)
    private Long id;
    /**
     * Mapeo de la placa del vehiculo
     */
    @Column(name = "Placa", nullable = false, length = 7)
    private String placa;
    /**
     * Mapeo de la fecha de recepcion de la placa
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_recepcion")
    private Calendar fechaRecepcion;
    /**
     * Mapeo del estado de la placa
     */
    @Column(name = "Estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPlaca estado;
    /**
     * Mapeo del id_automovil que se identifica en la entidad Automovil
     */
    @ManyToOne
    @JoinColumn(name = "id_automovil", nullable = false)
    private Automovil automovil;

    /**
     * Metodo que obtiene el id unico de placa
     *
     * @return el id de placa
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que establece el id de placa
     *
     * @param id de placa
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo del tipo de placa que obtiene el estado de placa
     *
     * @return el estado del tipo de placa
     */
    public TipoPlaca getEstado() {
        return estado;
    }

    /**
     * Metpdp que establece el estado del tipo de placa
     *
     * @param estado del tipo de placa
     */
    public void setEstado(TipoPlaca estado) {
        this.estado = estado;
    }

    /**
     * Metodo que obtiene la placa
     *
     * @return la placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Metodo que establece la placa
     *
     * @param placa a establecer
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Metodo que obtiene la fecha de recepcion de la placa
     *
     * @return la fecha de recepcion
     */
    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    /**
     * Metodo que establece la fecha de recepcion de la placa
     *
     * @param fechaRecepcion de la placa
     */
    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    /**
     * Metodo que obtiene el autmovil
     *
     * @return el automnovil
     */
    public Automovil getAutomovil() {
        return automovil;
    }

    /**
     * Metodo que se encarga de establecer el automovil
     *
     * @param automovil que tendra la placa0
     */
    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }

    /**
     * Metodo contructor que no recibe ningun parametro
     */
    public Placa() {

    }

    /**
     * Metodo que obtiene todos los atributos de la clase placas
     *
     * @return la placa con sus atributos establecidos en forma de cadena
     */
    @Override
    public String toString() {
        return "Placa{" + "id=" + id + ", placa=" + placa + ", fechaRecepcion=" + fechaRecepcion + ", estado=" + estado + ", automovil=" + automovil + '}';
    }

}
