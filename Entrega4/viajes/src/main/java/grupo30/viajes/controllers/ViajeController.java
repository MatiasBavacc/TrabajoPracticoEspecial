package grupo30.viajes.controllers;

import grupo30.viajes.dto.NuevoViajeDTO;
import grupo30.viajes.dto.ViajeDTO;
import grupo30.viajes.entity.Viaje;
import grupo30.viajes.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    /* endpoint todas ok  */
    @GetMapping("/")
    public ResponseEntity<?> findAllViajes() {
        return ResponseEntity.status(HttpStatus.OK).body(viajeService.findAllViajes());
    }

    /* endpoint una ok  */
    @GetMapping("/id/{id}")
    public ResponseEntity<ViajeDTO> getViajeById(@PathVariable String id) {
        ViajeDTO dto = viajeService.findViajeById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build(); // devuelve 404 si no existe
        }
        return ResponseEntity.ok(dto); // devuelve 200 con JSON
    }

    /* endpoint agregar ok  */
    @PostMapping("/agregar")
    public ResponseEntity<?> crearViaje(@RequestBody NuevoViajeDTO v) {
        return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.crearViaje(v));
    }



}

