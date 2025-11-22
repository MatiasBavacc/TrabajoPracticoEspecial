package grupo30.paradas.service;

import grupo30.paradas.dto.CoordenadasDTO;
import grupo30.paradas.dto.EstacionarDTO;
import grupo30.paradas.dto.ParadaDTO;
import grupo30.paradas.entity.Parada;
import grupo30.paradas.repository.ParadaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;

    /* endpoint todas ok  */
    @Transactional
    public List<Parada> findAllParadas() {
        return paradaRepository.findAll();
    }

    /* endpoint una ok  */
    @Transactional
    public ParadaDTO findParadaById(Long id) {
        Parada parada = paradaRepository.findById(id).orElse(null);
        if (parada == null) {
            return null;
        }
        return new ParadaDTO(parada.getId(), parada.getNombre(), parada.getLatitud(), parada.getLongitud(),
                parada.getQr(), parada.getDisponibles());
    }

    /* endpoint eliminar ok  */
    @Transactional
    public void eliminarParada(Long id) {
        paradaRepository.deleteById(id);
    }

    /* endpoint modificar falta  */
    @Transactional
    public Parada actualizarParada(Long id, Parada parada) {
        Parada existente = paradaRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Parada no encontrada con id: " + id ) );

        if( parada.getNombre() != null && !parada.getNombre().isEmpty() )
            existente.setNombre(parada.getNombre());

        if ( parada.getLatitud() != null && !parada.getLatitud().isNaN() )
            existente.setLatitud(parada.getLatitud());

        if ( parada.getLongitud() != null && !parada.getLongitud().isNaN() )
            existente.setLongitud(parada.getLongitud());

        if( parada.getQr() != null && !parada.getQr().isEmpty())
            existente.setQr(parada.getQr());

        if ( parada.getDisponibles() != null && !parada.getDisponibles().isEmpty() )
            existente.setDisponibles(parada.getDisponibles());

        return paradaRepository.save(existente);
    }

    /* endpoint agregar ok  */
    @Transactional
    public ParadaDTO crearParada(Parada parada) {
        Parada paradaGuardada = paradaRepository.save(parada);
        return new ParadaDTO(paradaGuardada.getId(),paradaGuardada.getNombre(), paradaGuardada.getLatitud(),
                paradaGuardada.getLongitud(), paradaGuardada.getQr(), null);
    }


    @Transactional
    public ParadaDTO estacionar(Long paradaId, EstacionarDTO monopatin) {
        Long monopatinId = monopatin.getMonopatinId();

        // 1️⃣ Buscar la parada
        Parada parada = paradaRepository.findById(paradaId).orElse(null);
        if(parada.getDisponibles().contains(monopatinId)){
            return null;
        }
        if (parada == null) {
            return null; // Se maneja en el controller como 404
        }

        // 2️⃣ Inicializar la lista si está null (seguridad)
        if (parada.getDisponibles() == null) {
            parada.setDisponibles(new ArrayList<>());
        }

        // 3️⃣ Evitar duplicados (si ya está estacionado)
        if (parada.getDisponibles().contains(monopatinId)) {
            throw new IllegalStateException("El monopatín ya está estacionado en esta parada");
        }

        // 4️⃣ Agregar el monopatín
        parada.getDisponibles().add(monopatinId);

        // 5️⃣ Guardar los cambios en la base
        Parada actualizada = paradaRepository.save(parada);

        // 6️⃣ Devolver DTO
        return new ParadaDTO(
                actualizada.getId(),
                actualizada.getNombre(),
                actualizada.getLatitud(),
                actualizada.getLongitud(),
                actualizada.getQr(),
                actualizada.getDisponibles()
        );
    }

    @Transactional
    public EstacionarDTO arrancar(Long paradaId) {
        // 1 Buscar la parada
        Parada parada = paradaRepository.findById(paradaId).orElse(null);
        if (parada == null) {
            return null; // Se maneja en el controller como 404
        }

        //  Validar lista de disponibles
        List<Long> disponibles = parada.getDisponibles();
        if (disponibles == null || disponibles.isEmpty()) {
           return null;
        }

        //  Tomar el primer monopatín y eliminarlo
        Long monopatinId = disponibles.get(0);
        disponibles.remove(0);

        //  Guardar los cambios
        Parada actualizada = paradaRepository.save(parada);

        //  Devolver DTO actualizado
        return new EstacionarDTO(monopatinId);
    }

    @Transactional
    public int monopatinesDisponibles(Long paradaId) {
        Parada parada = paradaRepository.findById(paradaId).orElse(null);
        if (parada == null) {
            return 0;
        }

        //  Validar lista de disponibles
        List<Long> disponibles = parada.getDisponibles();
        if (disponibles == null || disponibles.isEmpty()) {
            return 0;
        }

        return disponibles.size();
    }

    @Transactional
    public List<ParadaDTO> cercanos(CoordenadasDTO coor){
        Double latitud = coor.getLatitud();
        Double longitud = coor.getLongitud();

        List<Parada> lista = paradaRepository.findCercanos(latitud, longitud);
        List<ParadaDTO>salida = new ArrayList<>();
        for( Parada p : lista ){
            salida.add( new ParadaDTO( p.getId(), p.getNombre(), p.getLatitud(),
                    p.getLongitud(), p.getQr(), p.getDisponibles() ) );
        }
        return salida;
    }

    @Transactional
    public ParadaDTO ubicarMonopatin(Long id){
        Parada nueva = paradaRepository.ubicarMonopatin(id);

        if (nueva == null){
            return null;
        }

        return new ParadaDTO(nueva);
    }



}
