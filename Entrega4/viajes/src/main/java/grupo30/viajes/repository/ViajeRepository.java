package grupo30.viajes.repository;

import grupo30.viajes.dto.*;
import grupo30.viajes.entity.Viaje;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ViajeRepository extends MongoRepository<Viaje, String> {

    @Aggregation(pipeline = {
            "{ $group: { _id: \"$monopatin_id\", kl_monopatin: { $sum: \"$km_total_viaje\" } } }",
            "{ $project: { id_monopatin: \"$_id\", kl_monopatin: 1, _id: 0 } }",
            "{ $sort: { kl_monopatin: -1 } }"
    })
    List<ReporteKlDTO> obtenerKmPorMonopatin();

    @Aggregation(pipeline = {
            "{ $group: { _id: \"$monopatin_id\", cantidadViajes: { $sum: 1 } } }",
            "{ $project: { id_monopatin: \"$_id\", cantidadViajes: 1, _id: 0 } }",
            "{ $sort: { cantidadViajes: -1 } }"
    })
    List<MonopatinViajesDTO> obtenerCantidadViajesPorMonopatin();

    @Aggregation(pipeline = {
            "{ $group: { _id: \"$monopatin_id\", HorasEnViaje: { $sum: \"$horas_total_viaje\" }, horasPausa: { $sum: \"$horas_total_pausa\" } } }",
            "{ $project: { id_monopatin: \"$_id\", HorasEnViaje: 1, horasPausa: 1, _id: 0 } }",
            "{ $sort: { HorasEnViaje: -1 } }"
    })
    List<MonopatinHorasDTO> obtenerHorasPorMonopatin();

    @Aggregation(pipeline = {
            "{ $group: { _id: \"$usuario_id\", cantidadViajes: { $sum: 1 } } }",
            "{ $project: { id_usuario: \"$_id\", cantidadViajes: 1, _id: 0 } }",
            "{ $sort: { cantidadViajes: -1 } }"
    })
    List<UsuarioViajesDTO> obtenerUsuariosConMasViajes();

    @Aggregation(pipeline = {
            "{ $match: { \"detalles.fechaHora\": { $gte: ?0, $lt: ?1 } } }",
            "{ $group: { _id: \"$monopatin_id\", cantidadViajes: { $sum: 1 } } }",
            "{ $match: { cantidadViajes: { $gt: ?2 } } }",
            "{ $project: { id_monopatin: \"$_id\", cantidadViajes: 1, _id: 0 } }",
            "{ $sort: { cantidadViajes: -1 } }"
    })
    List<MonopatinViajesDTO> obtenerMonopatinesPorRangoFechasYMinViajes(Date fechaInicio, Date fechaFin, int minViajes);

    @Aggregation(pipeline = {
            "{ $match: { usuario_id: ?0, \"detalles.fechaHora\": { $gte: ?1, $lt: ?2 } } }",
            "{ $group: { _id: \"$usuario_id\", cantidadViajes: { $sum: 1 } } }",
            "{ $project: { id_usuario: \"$_id\", cantidadViajes: 1, _id: 0 } }"
    })
    List<UsuarioViajesDTO> obtenerCantidadViajesPorUsuarioEnPeriodo(Long usuarioId, Date fechaInicio, Date fechaFin);

    @Aggregation(pipeline = {
            "{ $match: { cuenta_id: ?0, \"detalles.fechaHora\": { $gte: ?1, $lt: ?2 } } }",
            "{ $group: { _id: \"$cuenta_id\", cantidadViajes: { $sum: 1 } } }",
            "{ $project: { numeroCuenta: \"$_id\", cantidadViajes: 1, _id: 0 } }"
    })
    List<CuentaViajesDTO> obtenerCantidadViajesPorCuentaEnPeriodo(Long numeroCuenta, Date fechaInicio, Date fechaFin);




}
