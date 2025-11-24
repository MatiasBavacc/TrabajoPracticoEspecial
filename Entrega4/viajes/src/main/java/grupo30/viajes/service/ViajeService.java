package grupo30.viajes.service;

import grupo30.viajes.dto.NuevoViajeDTO;
import grupo30.viajes.dto.ViajeDTO;
import grupo30.viajes.entity.Viaje;
import grupo30.viajes.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.annotation.Id;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    // Obtener todos los viajes
    public List<ViajeDTO> findAllViajes() {
        List<Viaje> viajes = viajeRepository.findAll();
        List<ViajeDTO> dtos = new ArrayList<>();
        for (Viaje viaje : viajes) {
            ViajeDTO dtoviaje = new ViajeDTO(viaje.getId(),
                    viaje.getUsuario_id(),
                    viaje.getCuenta_id(),
                    viaje.getMonopatin_id(),
                    viaje.getParada_inicio_id(),
                    viaje.getParada_fin_id(),
                    viaje.getKm_total_viaje(),
                    viaje.getHoras_total_viaje(),
                    viaje.getHoras_total_pausa(),
                    viaje.getDetalles());

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

        return new ViajeDTO(viajeG.getId(),
                viajeG.getUsuario_id(),
                viajeG.getCuenta_id(),
                viajeG.getMonopatin_id(),
                viajeG.getParada_inicio_id(),
                viajeG.getParada_fin_id(),
                viajeG.getKm_total_viaje(),
                viajeG.getHoras_total_viaje(),
                viajeG.getHoras_total_pausa(),
                viajeG.getDetalles());
    }
}
