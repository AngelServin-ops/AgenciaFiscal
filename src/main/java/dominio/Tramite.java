package dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Esta es una clase abstracta que representa un tramite en el sistema, viene
 * siendo una super clase para diferentes tipos de tramites a realizar
 *
 * @author Angel
 */
@Entity
@Table(name = "tramite")
@DiscriminatorColumn(name = "Tipo", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Tramite implements Serializable {

    /**
     * El id es el identificador unico de tramite
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_tramite", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Mapeo del tipo de tramite
     */
    @Column(name = "Tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTramite tipo;
    /**
     * Mapeo de la fecha de emision del tramite
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_emision", nullable = false)
    private Calendar fechaEmision;
    /**
     * Mapeo del costo del tramite a realizar
     */
    @Column(name = "Costo", nullable = false)
    private Double costo;
    /**
     * Mapeo del Id de la persona que realiza el tramite
     */
    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    /**
     * Metodo que obtiene el id unico de tramite
     *
     * @return el id de tramite
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo para establecer el id de tramite
     *
     * @param id de tramite
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo para obtener la fecha de emision del tramite
     *
     * @return la fecha de emision del tramite
     */
    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Metodo que establece la fecha de emision del tramite
     *
     * @param fechaEmision de tramite
     */
    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Metodo del tipoTramite que obtener el tipo de tamite
     *
     * @return el tipo de tramite
     */
    public TipoTramite getTipo() {
        return tipo;
    }

    /**
     * Metodo del tipoTramite que obtiene el tipo de tramite
     *
     * @param tipo de tramite
     */
    public void setTipo(TipoTramite tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo double que obtiene el costo del tamite
     *
     * @return el costo del tramite
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * Metodo double que establece el costo del tramite
     *
     * @param costo del tramite
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * Metodo persona que obtiene a persona que realiza el tramite
     *
     * @return la persona que realiza el tramite
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Metodo persona para establecer la persona que realiza el tramite
     *
     * @param persona a realizar el tramite
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * Metodo que obtiene todos los atributos de tramite
     *
     * @return los atributos de tramite en forma de cadena
     */
    @Override
    public String toString() {
        return "Tramite{" + "id=" + id + ", tipo=" + tipo + ", fechaEmision=" + fechaEmision + ", costo=" + costo + ", persona=" + persona + '}';
    }

}
