package grupo4.parada.repository;

import grupo4.tarifas.entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    /* endpoint modificar ok  */
    // Actualizar Tarifa
    @Query("UPDATE Tarifa t SET t.monto = :monto, t.montoExtra = :montoExtra, t.fecha = :fecha WHERE t.id = :id")
    void actualizarTarifa(@Param("id") Long id,
                          @Param("monto") double monto,
                          @Param("montoExtra") double montoExtra,
                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha);


    /* endpoint vigente ok  */
    // Buscar la tarifa vigente desde la fecha actual
    @Query("SELECT t FROM Tarifa t WHERE t.fecha <= CURRENT_DATE ORDER BY t.fecha DESC LIMIT 1")
    Tarifa findTarifaVigenteDesde();

}
