/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Esta clase Persona representa el solicitante a hacer un tramite
 *
 * @author Angel
 */
@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    /**
     * EL id unico que tiene cada persona al solicitar el tramite
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_persona", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Mapeo del rfc unico de la persona
     */
    @Column(name = "RFC", nullable = false, length = 13)
    private String rfc;
    /**
     * Mapeo del nombre o nombres de la persona
     */
//    @Convert(converter = Encriptacion.class)
    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;
    /**
     * Mapeo de los apellidos de la persona
     */
//    @Convert(converter = Encriptacion.class)
    @Column(name = "Apellido_Paterno", nullable = false, length = 100)
    private String apellidoPaterno;

//    @Convert(converter = Encriptacion.class)
    @Column(name = "Apellido_Materno", nullable = false, length = 100)
    private String apellidoMaterno;
    /**
     * Mapeo del telefono de la persona
     */
    @Column(name = "Telefono", nullable = false, length = 10)
    private String telefono;
    /**
     * Mapeo del telefono de la persona
     */
    @Column(name = "Fecha_Nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar fechaNacimiento;
    /**
     * Lista de vehiculos relacionados a persona
     */
    @OneToMany(mappedBy = "persona", targetEntity = Vehiculo.class)
    private List<Vehiculo> vehiculo;
    /**
     * Lista de tramites relacionados a persona
     */
    @OneToMany(mappedBy = "persona", targetEntity = Tramite.class)
    private List<Tramite> tramite;

    /**
     * Metodo constructor que no recibe ningun parametro
     */
    public Persona() {
    }

    /**
     * Metodo constructor que recibe los datos de la persona
     *
     * @param rfc que corresponde de la persona
     * @param nombre que corresponde de la persona
     * @param apellidoPaterno el apellido paterno que corresponde de la persona
     * @param apellidoMaterno el apellido materno que corresponde de la persona
     * @param telefono que corresponde de la persona
     * @param anio que corresponde de la persona
     * @param mes que corresponde de la persona
     * @param dia que corresponde de la persona
     */
    public Persona(String rfc, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, int anio, int mes, int dia) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = Calendar.getInstance();
        this.fechaNacimiento.set(Calendar.YEAR, anio);
        this.fechaNacimiento.set(Calendar.MONTH, mes);
        this.fechaNacimiento.set(Calendar.DAY_OF_MONTH, dia);
    }

    /**
     * Metodo que obtiene el rfc unico de la persona
     *
     * @return el rfc de la persona
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Metodo que obtiene el id unico de la persona
     *
     * @return el id de la persona
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que establece el id de la persona
     *
     * @param id de la persona
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que establece el rfc de la persona
     *
     * @param rfc de la persona
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Metodo que obtiene el nombre de la persona
     *
     * @return el nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que establece el nombre de la persona
     *
     * @param nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que obtiene el apellido paterno de la persona
     *
     * @return el apellido paterno de la persona
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Metodo que establece el apellido paterno de la persona
     *
     * @param apellidoPaterno de la persona
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Metodo que obtiene el apellido materno de la persona
     *
     * @return el apellido materno de la persona
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Metodo que establece el apellido materno de la persona
     *
     * @param apellidoMaterno de la persona
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Metodo que obtiene el telefono de la persona
     *
     * @return el telefono de la persona
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo que establece el telefono de la persona
     *
     * @param telefono de la persona
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo que obtiene la fecha de nacimiento "dia, mes, anio" de la persona
     *
     * @return la fecha de nacimiento de la persona
     */
    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Metodo que establece la fecha de nacimiento "dia, mes, anio" de la
     * persona
     *
     * @param fechaNacimiento de la persona
     */
    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Metodo que obtiene la lista de vehiculos "automoviles" de la persona
     *
     * @return la lista de vehiculos de la persona
     */
    public List<Vehiculo> getVehiculo() {
        return vehiculo;
    }

    /**
     * Metodo que establece la lista de vehiculos "automoviles" de la persona
     *
     * @param vehiculo de la lista de la persona
     */
    public void setVehiculo(List<Vehiculo> vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * Metodo que obtiene la lista de tramites solicitados de la persona
     *
     * @return el tramite de la persona
     */
    public List<Tramite> getTramite() {
        return tramite;
    }

    /**
     * Metodo que establece la lista de tramites solicitados de la persona
     *
     * @param tramite de la persona
     */
    public void setTramite(List<Tramite> tramite) {
        this.tramite = tramite;
    }

    /**
     * Metodo que obtiene todos los atributos de la clase persona
     *
     * @return la persona con sus atributos establecidos en forma de cadena
     */
    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", rfc=" + rfc + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", telefono=" + telefono + '}';
    }
}
