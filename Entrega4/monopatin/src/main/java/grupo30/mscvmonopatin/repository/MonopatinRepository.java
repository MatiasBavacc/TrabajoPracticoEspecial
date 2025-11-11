package grupo30.mscvmonopatin.repository;

import grupo30.mscvmonopatin.entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("""
        SELECT m 
        FROM Monopatin m
        ORDER BY 
            CASE WHEN :order = 'km' THEN m.km END DESC,
            CASE WHEN :order = 'horas' THEN m.horas END DESC
        """)

    List<Monopatin> findAllByOrder(@Param("order") String orden);
}
