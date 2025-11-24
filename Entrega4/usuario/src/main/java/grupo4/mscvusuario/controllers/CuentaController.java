package grupo4.mscvusuario.controllers;

import grupo4.mscvusuario.dto.CuentaDTO;
import grupo4.mscvusuario.entity.Cuenta;
import grupo4.mscvusuario.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    /* Cuentas Ok */
    @GetMapping("/")
    public ResponseEntity<List<CuentaDTO>> findCuentasAll(){
        List<CuentaDTO> cuentas = cuentaService.findCuentaAll();
        if(cuentas.isEmpty()){
            return ResponseEntity.ok(cuentas);
        }
        return ResponseEntity.ok(cuentas);
    }

    /* Cuentas Ok */
    @GetMapping("/id/{id}")
    public ResponseEntity<CuentaDTO> findCuentaById(@PathVariable Long id){
        CuentaDTO cuenta = cuentaService.findCuentaById(id);
        if(cuenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuenta);
    }

    /* Cuentas Ok */
    @GetMapping("/id-usuario/{id}")
    public ResponseEntity<List<CuentaDTO>> findCuentaByUser(@PathVariable Long id){
        List<CuentaDTO> cuentas = cuentaService.findCuentaByUser(id);

        if (cuentas == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cuentas);
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> crearCuenta(@RequestBody Cuenta c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.saveCuenta(c));
    }

    /* endpoint delete ok  */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id) {
        CuentaDTO actual  = cuentaService.findCuentaById(id);
        cuentaService.deleteCuenta(id);
        if(actual == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actual);
    }

    /* endpoint put ok  */
    @PutMapping("/modificar")
    public ResponseEntity<?> modificarCuenta(@RequestBody Cuenta c) {
        CuentaDTO actual  = cuentaService.findCuentaById(c.getId());
        if(actual == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.updateCuenta(c));
    }
}
