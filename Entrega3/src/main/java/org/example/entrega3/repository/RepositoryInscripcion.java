package org.example.entrega3.repository;

import org.example.entrega3.dto.DTOReporte;
import org.example.entrega3.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryInscripcion extends JpaRepository<Inscripcion, Integer>{
    @Query("SELECT new org.example.entrega3.dto.DTOReporte(ec.fechaEgreso, c.nombreCarrera, COUNT(ec), (SELECT COUNT(ec2) FROM Inscripcion ec2 WHERE ec2.carrera.nombreCarrera = c.nombreCarrera)) " +
            "FROM Inscripcion ec " +
            "JOIN ec.carrera c " +
            "WHERE ec.fechaEgreso != 0 " +
            "GROUP BY ec.fechaEgreso, c.nombreCarrera " +
            "ORDER BY c.nombreCarrera, ec.fechaEgreso")
    List<DTOReporte> getReportesCarreras();
}