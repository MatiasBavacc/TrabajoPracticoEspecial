package grupo30.viajes.controllers;

import grupo30.viajes.dto.*;
import grupo30.viajes.entity.Viaje;
import grupo30.viajes.repository.ViajeRepository;
import grupo30.viajes.service.ViajeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;
    @Autowired
    private ViajeRepository viajeRepository;

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

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> eliminarViaje(@PathVariable String id) {
        ViajeDTO actual  = viajeService.findViajeById(id);
        if (actual == null) {
            return ResponseEntity.status(HttpStatus.OK).body(actual);
        }
        viajeService.eliminarViaje(id);
        return ResponseEntity.ok(actual);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> actualizarViaje(@PathVariable String id, @RequestBody NuevoDetalleDTO detalle) {
        try {
            ViajeDTO actualizado = viajeService.actualizarViaje(id, detalle);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /* endpoint todas ok  */
    @GetMapping("/XKm")
    public ResponseEntity<?> findAllMonopatinKL() {
        return ResponseEntity.status(HttpStatus.OK).body(viajeService.reporteXKL());
    }

    @GetMapping("/XViajes")
    public ResponseEntity<?> findAllMonopatinViajes() {
        return ResponseEntity.status(HttpStatus.OK).body(viajeService.reporteXViajes());
    }

    @GetMapping("/XHoras")
    public ResponseEntity<?> findAllMonopatinHora() {
        return ResponseEntity.status(HttpStatus.OK).body(viajeService.reporteXhoras());
    }

    @GetMapping("/XUsuario")
    public ResponseEntity<?> findAllUsuariosMasViajados() {
        return ResponseEntity.status(HttpStatus.OK).body(viajeService.reporteXUsuarios());
    }

    @GetMapping("/monopatines")
    public ResponseEntity<List<MonopatinViajesDTO>> getMonopatinesPorAnioYMinViajes(
            @RequestParam int year,
            @RequestParam int minViajes) {

        List<MonopatinViajesDTO> resultado = viajeService.obtenerMonopatinesPorAnioYMinViajes(year, minViajes);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/usuarios/viajes")
    public ResponseEntity<List<UsuarioViajesDTO>> getCantidadViajesPorUsuarioEnPeriodo(
            @RequestParam Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        Date inicio = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fin = Date.from(fechaFin.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<UsuarioViajesDTO> resultado =
                viajeService.obtenerCantidadViajesPorUsuarioEnPeriodo(usuarioId, inicio, fin);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/cuenta/viajes")
    public ResponseEntity<List<CuentaViajesDTO>> getCantidadViajesPorCuentaEnPeriodo(
            @RequestParam Long cuentaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        Date inicio = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fin = Date.from(fechaFin.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<CuentaViajesDTO> resultado =
                viajeService.obtenerCantidadViajesPorCuentaEnPeriodo(cuentaId, inicio, fin);

        return ResponseEntity.ok(resultado);
    }
}




