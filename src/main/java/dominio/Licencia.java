/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Esta es la clase licencia que representa un tipo de tramite
 *
 * @author Angel
 */
@Entity
@PrimaryKeyJoinColumn(name = "id_tramite_licencia")
@DiscriminatorValue("Licencia")
@Table(name = "tramite_licencia")
public class Licencia extends Tramite {

    /**
     * El id es el identificador unico de cada licencia
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_licencia", nullable = false)
    private Long id;
    /**
     * La vigencia representa el periodo de tiempo en la cual es valida
     */
    @Column(name = "Vigencia", nullable = false)
    private Integer vigencia;
    /**
     * El TipoLicencia representa el tipo de licencia que se esta solicitando
     */
    @Column(name = "Tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoLicencia tipoLicencia;

    /**
     * Metodo constructor que no recibe ningun parametro
     */
    public Licencia() {
    }

    /**
     * Metodo que obtiene el id unico de licencia
     *
     * @return como el nuevo id para licencia
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Metodo que se encarga de establecer el id de licencia
     *
     * @param id como el nuevo identificador para licencia
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que obtiene el tipo de licencia para hacer el tramite
     *
     * @return el tipo de licencia
     */
    public TipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }

    /**
     * Metodo que establece el tipo de licencia para hacer el tramite
     *
     * @param tipoLicencia a establecer
     */
    public void setTipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    /**
     * Metodo que obtiene la vigencia del tramite de licencia
     *
     * @return la vigencia
     */
    public Integer getVigencia() {
        return vigencia;
    }

    /**
     * Metodo que establece la vigencia del tramite de licencia
     *
     * @param vigencia de la licencia
     */
    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
    }

    /**
     * Metodo que obtiene todos los atributos de la clase licencia
     *
     * @return la licencia con sus atributos establecidos en forma de cadena
     */
    @Override
    public String toString() {
        return "Licencia{" + "id=" + id + ", vigencia=" + vigencia + ", tipoLicencia=" + tipoLicencia + '}';
    }

}
