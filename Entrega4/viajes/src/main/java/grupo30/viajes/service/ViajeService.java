package grupo30.viajes.service;

import grupo30.viajes.dto.*;
import grupo30.viajes.entity.DetalleViaje;
import grupo30.viajes.entity.Estado_viaje;
import grupo30.viajes.entity.Viaje;
import grupo30.viajes.repository.ViajeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.annotation.Id;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    // Obtener todos los viajes
    public List<ViajeDTO> findAllViajes() {
        List<Viaje> viajes = viajeRepository.findAll();
        List<ViajeDTO> dtos = new ArrayList<>();

        for (Viaje viaje : viajes) {
            ViajeDTO dtoviaje = new ViajeDTO(viaje);
            dtos.add(dtoviaje);
        }

        return dtos;
    }

    // Obtener un viaje por ID
    public ViajeDTO findViajeById(String id) {
        return viajeRepository.findById(id)
                .map(viaje -> new ViajeDTO(
                        viaje.getId(),
                        viaje.getUsuario_id(),
                        viaje.getCuenta_id(),
                        viaje.getMonopatin_id(),
                        viaje.getParada_inicio_id(),
                        viaje.getParada_fin_id(),
                        viaje.getKm_total_viaje(),
                        viaje.getHoras_total_viaje(),
                        viaje.getHoras_total_pausa(),
                        viaje.getDetalles()
                ))
                .orElse(null);
    }

    /* endpoint agregar ok  */
    @Transactional
    public ViajeDTO crearViaje(NuevoViajeDTO v) {
        Viaje viaje = new Viaje(v);
        Viaje viajeG = viajeRepository.save(viaje);
        return new ViajeDTO(viajeG);
    }

    @Transactional
    public void eliminarViaje(String id) {
        viajeRepository.deleteById(id);
    }

    public ViajeDTO actualizarViaje(String id, NuevoDetalleDTO detalle) {

        Viaje existente = viajeRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Viaje no encontrado con id: " + id ) );

         //si el viaje no finalizó, y es diferente al ultimo, lo cambia
        if( !existente.getDetalles().getLast().getEstado().equals(Estado_viaje.FINALIZADO) &&
                !existente.getDetalles().getLast().getEstado().equals(detalle.getEstado())) {
            DetalleViaje nuevo = new DetalleViaje(detalle);
            existente.addDetalle(nuevo);
            viajeRepository.save(existente);
        }

        return new ViajeDTO(existente);
    }

    public List<ReporteKlDTO> reporteXKL() {
        return this.viajeRepository.obtenerKmPorMonopatin();
    }

    public List<MonopatinViajesDTO> reporteXViajes() {
        return this.viajeRepository.obtenerCantidadViajesPorMonopatin();
    }

    public List<MonopatinHorasDTO> reporteXhoras() {
        return this.viajeRepository.obtenerHorasPorMonopatin();
    }

    public List<UsuarioViajesDTO> reporteXUsuarios() {
        return this.viajeRepository.obtenerUsuariosConMasViajes();
    }

    public List<MonopatinViajesDTO> obtenerMonopatinesPorAnioYMinViajes(int year, int minViajes) {
        Calendar inicio = Calendar.getInstance();
        inicio.set(year, Calendar.JANUARY, 1, 0, 0, 0);

        Calendar fin = Calendar.getInstance();
        fin.set(year + 1, Calendar.JANUARY, 1, 0, 0, 0);

        return viajeRepository.obtenerMonopatinesPorRangoFechasYMinViajes(
                inicio.getTime(),
                fin.getTime(),
                minViajes
        );
    }

    public List<UsuarioViajesDTO> obtenerCantidadViajesPorUsuarioEnPeriodo(Long usuarioId, Date inicio, Date fin) {
        return viajeRepository.obtenerCantidadViajesPorUsuarioEnPeriodo(usuarioId, inicio, fin);
    }

    public List<CuentaViajesDTO> obtenerCantidadViajesPorCuentaEnPeriodo(Long cId, Date inicio, Date fin) {
        return viajeRepository.obtenerCantidadViajesPorCuentaEnPeriodo(cId, inicio, fin);
    }

}

