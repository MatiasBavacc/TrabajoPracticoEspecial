package grupo30.facturacion.repository;


import grupo30.facturacion.entity.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FacturaRepository extends MongoRepository<Factura, String> {

    List<Factura> findByUsuario_Id(Long usuarioId);

    // Busca facturas cuya fecha esté entre dos valores
    List<Factura> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);


}
