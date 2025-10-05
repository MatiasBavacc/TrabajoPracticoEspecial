package entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class Inscripcion implements Serializable {
    // Atributos
    @Id
    private int idInscripcion;

    @ManyToOne
    private Carrera carrera;

    @ManyToOne
    private Estudiante alumno;

    @Column
    private int fechaInscripcion;

    @Column(nullable = true)
    private int fechaEgreso;

    @Column
    private int antiguedad;


    // Constructores
    public Inscripcion() {}

    public Inscripcion( Estudiante alumno,Carrera carrera, int fechaInscripcion, int fechaEgreso,
                       int antiguedad) {
        this.carrera = carrera;
        this.alumno = alumno;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaEgreso = fechaEgreso;
        this.antiguedad = antiguedad;
    }

    public Inscripcion(int idInscripcion, Carrera carrera, Estudiante alumno, int fechaInscripcion,
                       int fechaEgreso, int antiguedad) {
        this(alumno,carrera, fechaInscripcion, fechaEgreso, antiguedad);
        this.idInscripcion = idInscripcion;
    }

    // Getters and setters
    public int getIdInscripcion() {
        return idInscripcion;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Estudiante getAlumno() {
        return alumno;
    }

    public void setAlumno(Estudiante alumno) {
        this.alumno = alumno;
    }

    public int getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(int fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(int fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "idInscripcion=" + idInscripcion +
                ", carrera=" + carrera.getIdCarrera() +
                ", alumno=" + alumno.getIdEstudiante() +
                ", fechaInscripcion=" + fechaInscripcion +
                ", fechaEgreso=" + fechaEgreso +
                ", antiguedad=" + antiguedad +
                '}';
    }
}