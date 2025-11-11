package grupo30.mscvmonopatin.services;

import grupo30.mscvmonopatin.dtos.MonopatinDTO;
import grupo30.mscvmonopatin.entity.Monopatin;
import grupo30.mscvmonopatin.repository.MonopatinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository repository;

    /* endpoint todas ok  */
    @Transactional
    public List<Monopatin> findAll() {
        return repository.findAll();
    }

    /* endpoint una ok  */
    @Transactional
    public Monopatin findById(Long id) {
        Monopatin monopatin = repository.findById(id).orElse(null);
        if (monopatin == null) {
            return null;
        }
        return monopatin;
    }

    /* endpoint delete ok  */
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }


    /* endpoint agregar ok  */
    @Transactional
    public MonopatinDTO crearMonopatin(Monopatin nuevoMonopatin) {
        Monopatin monopatinGuardado = repository.save(nuevoMonopatin);
        return new MonopatinDTO(monopatinGuardado);
    }

    /* endpoint modificar ok  */
    @Transactional
    public Monopatin actualizarMonopatin(Long id, Monopatin monopatin) {
        Monopatin existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarifa no encontrada con id: " + id));

        if ( monopatin.getEstadoMonopatin() != null )
            existente.setEstadoMonopatin(monopatin.getEstadoMonopatin());

        if ( monopatin.getLatitud() != null && !monopatin.getLatitud().isNaN() )
            existente.setLatitud(monopatin.getLatitud());

        if ( monopatin.getLongitud() != null && !monopatin.getLongitud().isNaN() )
            existente.setLongitud(monopatin.getLongitud());

        if ( monopatin.getPatente() != null && !monopatin.getPatente().isEmpty() )
            existente.setPatente(monopatin.getPatente());

        if ( monopatin.getKm() != null && !monopatin.getKm().isNaN() )
            existente.setKm(monopatin.getKm());

        if( monopatin.getHoras() != null && !monopatin.getHoras().isNaN() )
            existente.setHoras(monopatin.getHoras());

        return repository.save(existente);
    }

    @Transactional
    public List<Monopatin> ordenarMonopatines(String orden) {
        List<Monopatin> monopatines = repository.findAllByOrder(orden);
        return monopatines;
    }
}
