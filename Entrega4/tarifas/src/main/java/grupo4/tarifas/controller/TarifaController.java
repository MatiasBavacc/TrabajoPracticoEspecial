package grupo4.tarifas.controller;

import grupo4.tarifas.entity.Tarifa;
import grupo4.tarifas.service.TarifaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    /* endpoint modificar ok  */
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarTarifa(@RequestBody Tarifa tarifa) {
        try {
            Tarifa actualizada = tarifaService.actualizarTarifa(tarifa);
            return ResponseEntity.ok(actualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /* endpoint eliminar ok  */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> eliminarTarifa(@PathVariable Long id) {
       tarifaService.eliminarTarifa(id);
        return ResponseEntity.ok("Tarifa eliminada correctamente");
    }

    /* endpoint agregar ok  */
    @PostMapping("/agregar")
    public ResponseEntity<?> crearTarifa(@RequestBody Tarifa tarifa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.crearTarifa(tarifa));
    }

    /* endpoint todas ok  */
    @GetMapping("/")
    public ResponseEntity<?> findAllTarifas() {
        return ResponseEntity.status(HttpStatus.OK).body(tarifaService.findAllTarifas());
    }

    /* endpoint una ok  */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> findTarifaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tarifaService.findTarifaById(id));
    }

    /* endpoint vigente ok  */
    @GetMapping("/vigente")
    public ResponseEntity<?> findTarifaByFecha() {
        return ResponseEntity.status(HttpStatus.OK).body(tarifaService.findTarifaByFecha());
    }

}

