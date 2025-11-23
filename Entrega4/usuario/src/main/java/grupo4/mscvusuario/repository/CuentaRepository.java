package grupo4.mscvusuario.repository;

import org.springframework.data.repository.query.Param;
import grupo4.mscvusuario.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long>{
}
