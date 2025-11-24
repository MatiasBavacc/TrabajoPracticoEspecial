package grupo30.viajes.repository;

import grupo30.viajes.entity.Viaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends MongoRepository<Viaje, String> {




}
