package grupo4.tarifas.service;

import grupo4.tarifas.dto.TarifaDTO;
import grupo4.tarifas.entity.Tarifa;
import grupo4.tarifas.repository.TarifaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    /* endpoint modificar ok  */
    @Transactional
    public Tarifa actualizarTarifa(Tarifa tarifa) {
        Tarifa existente = tarifaRepository.findById(tarifa.getId())
                .orElseThrow(() -> new EntityNotFoundException("Tarifa no encontrada con id: " + tarifa.getId()));

        existente.setMonto(tarifa.getMonto());
        existente.setMontoExtra(tarifa.getMontoExtra());
        existente.setFecha(tarifa.getFecha());

        return tarifaRepository.save(existente);
    }

    /* endpoint eliminar ok  */
    @Transactional
    public void eliminarTarifa(Long id) {
        tarifaRepository.deleteById(id);
    }

    /* endpoint agregar ok  */
    @Transactional
    public TarifaDTO crearTarifa(Tarifa tarifa) {
        Tarifa tarifaGuardada = tarifaRepository.save(tarifa);
        return new TarifaDTO(tarifaGuardada.getMonto(), tarifaGuardada.getMontoExtra(),
                tarifaGuardada.getFecha());
    }

    /* endpoint todas ok  */
    @Transactional
    public List<Tarifa> findAllTarifas() {
        return tarifaRepository.findAll();
    }

    /* endpoint una ok  */
    @Transactional
    public TarifaDTO findTarifaById(Long id) {
        Tarifa tarifa = tarifaRepository.findById(id).orElse(null);
        if (tarifa == null) {
            return null;
        }
        return new TarifaDTO(tarifa.getMonto(), tarifa.getMontoExtra(), tarifa.getFecha());
    }

    /* endpoint vigente ok  */
    @Transactional
    public TarifaDTO findTarifaByFecha() {
        Tarifa tarifa = tarifaRepository.findTarifaVigenteDesde();
        if (tarifa == null) {
            return null;
        }
        return new TarifaDTO(tarifa.getMonto(), tarifa.getMontoExtra(), tarifa.getFecha());
    }

}
