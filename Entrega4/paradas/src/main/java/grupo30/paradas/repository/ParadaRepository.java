package grupo30.paradas.repository;

import grupo30.paradas.dto.ParadaDTO;
import grupo30.paradas.entity.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface ParadaRepository extends JpaRepository<Parada, Long> {

    @Query("""
        SELECT p 
        FROM Parada p
        ORDER BY ((p.latitud - :latitud) * (p.latitud - :latitud) +
                  (p.longitud - :longitud) * (p.longitud - :longitud)) ASC
        """)
    List<Parada> findCercanos(@Param("latitud") Double latitud,
                              @Param("longitud") Double longitud);
}
