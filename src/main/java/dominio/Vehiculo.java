package dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Esta clase abstracta representa un vehiculo en el sistema
 *
 * @author Angel
 */
    @Entity
    @Table(name = "vehiculo")
    @Inheritance(strategy = InheritanceType.JOINED)
    public abstract class Vehiculo implements Serializable {

        /**
         * El id es el identificador unico de vehiculo
         */
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_vehiculo", nullable = false)
        private Long id;
        /**
         * Mapeo del modelo del vehiculo
         */
        @Column(name = "Modelo", nullable = false, length = 10)
        private Integer modelo;

        /**
         * Mapeo de la serie del vehiculo
         */
        @Column(name = "Serie", nullable = false, length = 7)
        private String serie;
        /**
         * Mapeo del color del vehiculo
         */
        @Column(name = "Color", nullable = false, length = 100)
        private String color;
        /**
         * Mapeo de la linea del vehiculo
         */
        @Column(name = "Linea", nullable = false, length = 100)
        private String linea;
        /**
         * Mapeo de la marca del vehiculo
         */
        @Column(name = "Marca", nullable = false, length = 100)
        private String marca;
        /**
         * Mapeo del id de la persona que le pertenece el vehiculo
         */
        @ManyToOne
        @JoinColumn(name = "id_persona", nullable = false)
        private Persona persona;

        /**
         * Metodo que obtiene el id unico de vehiculo
         *
         * @return el id de vehiculo
         */
        public Long getId() {
            return id;
        }

        /**
         * Metodo que establece el id de vehiculo
         *
         * @param id de vehiculo
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * Metodo que obtiene el modelo del vehiculo
         *
         * @return el modelo de vehiculo
         */
        public Integer getModelo() {
            return modelo;
        }

        /**
         * Metodo que establece el modelo del vehiculo
         *
         * @param modelo del vehiculo
         */
        public void setModelo(Integer modelo) {
            this.modelo = modelo;
        }

        /**
         * Metodo que establece la serie el vehiculo
         *
         * @return el vehiculo
         */
        public String getSerie() {
            return serie;
        }

        /**
         * Metodo que establece la serie del vehiculo
         *
         * @param serie del vehiculo
         */
        public void setSerie(String serie) {
            this.serie = serie;
        }

        /**
         * Metpdp que obtiene el color del vehiculo
         *
         * @return el color del vehiculo
         */
        public String getColor() {
            return color;
        }

        /**
         * Metodo que establece el color del vehiculo
         *
         * @param color del vehiculo
         */
        public void setColor(String color) {
            this.color = color;
        }

        /**
         * Metodo que obtiene la linea del vehiculo
         *
         * @return la linea del vehiculo
         */
        public String getLinea() {
            return linea;
        }

        /**
         * Metodo que establece la linea del vehiculo
         *
         * @param linea del vehiculo
         */
        public void setLinea(String linea) {
            this.linea = linea;
        }

        /**
         * Metodo que obtiene la marca del vehiculo
         *
         * @return la marca del vehiculo
         */
        public String getMarca() {
            return marca;
        }

        /**
         * Metodo que establece la marca del vehiculo
         *
         * @param marca del vehiculo
         */
        public void setMarca(String marca) {
            this.marca = marca;
        }

        /**
         * Metodo que establece la persona que tiene el vehiculo
         *
         * @return la persona que tiene vehiculo
         */
        public Persona getPersona() {
            return persona;
        }

        /**
         * Metodo que obtiene a la persona que tiene el vehiculo
         *
         * @param persona que tiene el vehiculo
         */
        public void setPersona(Persona persona) {
            this.persona = persona;
        }

        /**
         * Metodo que obtiene todos los atributos de la clase abstracta vehiculo
         *
         * @return el vehiculo con sus atributos establecidos en forma de cadena
         */
        @Override
        public String toString() {
            return "Vehiculo{" + "id=" + id + ", modelo=" + modelo + ", serie=" + serie + ", color=" + color + ", linea=" + linea + ", marca=" + marca + '}';
        }
    }
