package grupo30.facturacion.controller;

import grupo30.facturacion.entity.Factura;
import grupo30.facturacion.entity.Viaje;
import grupo30.facturacion.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.findById(id));
    }

    @GetMapping("/usuario/id/{id}")
    public ResponseEntity<?> findByUsuarioId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.findByUsuarioId(id));
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Factura factura) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaService.save(factura));
    }

    @PostMapping("/facturar/")
    public ResponseEntity<Factura> save(@RequestBody Viaje viaje) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaService.crearFactura(viaje));
    }


    @GetMapping("/fechaBetween")
    public ResponseEntity<?>findByFechaBetween(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date fecha1,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date fecha2) {
        return ResponseEntity.status(HttpStatus.OK).body(facturaService.findByFechaBetween(fecha1, fecha2));
    }
}
