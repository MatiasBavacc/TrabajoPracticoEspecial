package entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera implements Serializable {

        //Atributos
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int idCarrera;

        @Column
        private String nombreCarrera;

        @Column
        private int duracionCarrera;

        //Relacion
        @OneToMany(mappedBy="carrera", fetch=FetchType.LAZY)
        private List<Inscripcion> inscripciones;

        // Constructores
        public Carrera() {
            this.inscripciones = new ArrayList<>();
        }

        public Carrera(int idCarrera) {
            this();
            this.idCarrera = idCarrera;
        }

        public Carrera(String nombreCarrera) {
            this();
            this.nombreCarrera = nombreCarrera;
        }

        public Carrera( String nombreCarrera, int duracionCarrera) {
            this();
            this.nombreCarrera = nombreCarrera;
            this.duracionCarrera = duracionCarrera;
        }

        // Getters y Setters
        public int getIdCarrera() {
            return idCarrera;
        }

        public String getNombreCarrera() {
            return nombreCarrera;
        }

        public void setNombreCarrera(String nombreCarrera) {
            this.nombreCarrera = nombreCarrera;
        }
}
