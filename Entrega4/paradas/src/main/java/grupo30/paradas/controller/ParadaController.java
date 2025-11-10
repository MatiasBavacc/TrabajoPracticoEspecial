package grupo30.paradas.controller;

import grupo30.paradas.dto.CoordenadasDTO;
import grupo30.paradas.dto.EstacionarDTO;
import grupo30.paradas.dto.ParadaDTO;
import grupo30.paradas.entity.Parada;
import grupo30.paradas.service.ParadaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parada")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    /* endpoint todas ok  */
    @GetMapping("/")
    public ResponseEntity<?> findAllParadas() {
        return ResponseEntity.status(HttpStatus.OK).body(paradaService.findAllParadas());
    }

    /* endpoint una ok  */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> findParadaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(paradaService.findParadaById(id));
    }

    /* endpoint eliminar ok  */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> eliminarParada(@PathVariable Long id) {
        ParadaDTO actual  = paradaService.findParadaById(id);
        if ( actual == null ) {
            return ResponseEntity.status(HttpStatus.OK).body(actual);
        }
        paradaService.eliminarParada(id);
        return ResponseEntity.ok(actual);
    }

    /* endpoint modificar ok  */
    @PutMapping("/id/{id}")
    public ResponseEntity<?> actualizarParada(@PathVariable Long id, @RequestBody Parada parada) {
        try {
            Parada actualizada = paradaService.actualizarParada(id, parada);
            return ResponseEntity.ok(actualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /* endpoint agregar ok  */
    @PostMapping("/agregar")
    public ResponseEntity<?> crearParada(@RequestBody Parada parada) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paradaService.crearParada(parada));
    }

    /* endpoint estacionar ok */
    @PostMapping("/estacionar/{id}")
    public ResponseEntity<?> estacionarEnParada(@PathVariable Long id, @RequestBody EstacionarDTO monopatin) {
        ParadaDTO estacionada = paradaService.estacionar(id, monopatin);
        if( estacionada == null ) {
            return ResponseEntity.status(HttpStatus.OK).body(estacionada);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(estacionada);
    }

    /* endpoint arrancar*/
    @DeleteMapping("/arrancar/{id}")
    public ResponseEntity<?> arrancarDeParada(@PathVariable Long id) {
        ParadaDTO actual  = paradaService.findParadaById(id);
        if ( actual == null ) {
            return ResponseEntity.status(HttpStatus.OK).body(actual);
        }
        return ResponseEntity.status(HttpStatus.OK).body(paradaService.arrancar(id));
    }

    /* endpoint disponibles ok  */
    @GetMapping("/disponibles/{id}")
    public ResponseEntity<?> disponiblesParada(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(paradaService.monopatinesDisponibles(id));
    }

    /* endpoint disponibles ok  */
    @GetMapping("/cercanos")
    public ResponseEntity<?> cercanos(@RequestBody CoordenadasDTO coor) {
        return ResponseEntity.status(HttpStatus.OK).body(paradaService.cercanos(coor));
    }

}

