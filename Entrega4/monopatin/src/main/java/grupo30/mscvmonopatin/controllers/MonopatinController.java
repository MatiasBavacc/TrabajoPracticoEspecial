package grupo30.mscvmonopatin.controllers;

import feign.HeaderMap;
import grupo30.mscvmonopatin.dtos.MonopatinDTO;
import grupo30.mscvmonopatin.entity.Monopatin;
import grupo30.mscvmonopatin.entity.*;
import grupo30.mscvmonopatin.services.MonopatinService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {

    private final MonopatinService service;

    public MonopatinController(MonopatinService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<MonopatinDTO>> findAll() {
        List<MonopatinDTO> salida = new ArrayList<>();
        List<Monopatin> monopatines = service.findAll();
        for(Monopatin m: monopatines){
            salida.add(new MonopatinDTO(m));
        }
        return ResponseEntity.ok(salida);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MonopatinDTO> findById(@PathVariable Long id) {
        MonopatinDTO salida;
        Monopatin monopatin = service.findById(id);
        if (monopatin == null) {
            return ResponseEntity.ok(null);
        }
        salida =new MonopatinDTO(monopatin);
        return ResponseEntity.ok(salida);
    }

    /* endpoint agregar ok  */
    @PostMapping("/agregar")
    public ResponseEntity<?> crearMonopatin(@RequestBody Monopatin monopatin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearMonopatin(monopatin));
    }

    /* endpoint delete ok  */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Monopatin actual  = service.findById(id);
        if ( actual == null ) {
            return ResponseEntity.status(HttpStatus.OK).body(actual);
        }
        service.delete(id);
        return ResponseEntity.ok(new MonopatinDTO(actual));

    }

    /* endpoint modificar ok  */
    @PutMapping("/id/{id}")
    public ResponseEntity<?> actualizarTarifa(@PathVariable Long id,@RequestBody Monopatin monopatin) {
        try {
            Monopatin actualizada = service.actualizarMonopatin(id,monopatin);
            return ResponseEntity.ok(actualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /* endpoint ordenar ok  */
    @GetMapping("/ordenar")
    public ResponseEntity<List<MonopatinDTO>> ordenarMonopatines(@RequestParam  String orden) {
        List<MonopatinDTO> salida = new ArrayList<>();
        List<Monopatin> monopatines = service.ordenarMonopatines(orden);
        for(Monopatin m: monopatines){
            salida.add(new MonopatinDTO(m));
        }
        return ResponseEntity.ok(salida);
    }

}
