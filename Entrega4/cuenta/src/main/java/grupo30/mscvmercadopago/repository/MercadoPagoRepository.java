package grupo30.mscvmercadopago.repository;

import feign.Param;
import grupo30.mscvmercadopago.entity.MercadoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MercadoPagoRepository extends JpaRepository<MercadoCuenta, Long> {

    @Query("SELECT m FROM MercadoCuenta m WHERE m.userId = :id")
    MercadoCuenta findByIdUser(@Param String id);
}
