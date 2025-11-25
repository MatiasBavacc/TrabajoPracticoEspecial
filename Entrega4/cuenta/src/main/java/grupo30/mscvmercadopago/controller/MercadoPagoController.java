package grupo30.mscvmercadopago.controller;

import grupo30.mscvmercadopago.dto.MercadoCuentaDTO;
import grupo30.mscvmercadopago.service.MercadoPagoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mercadopago")
public class MercadoPagoController {
    @Autowired
    private MercadoPagoService mpService;

    /* endpoint todas ok  */
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(mpService.findAll());
    }

    /* endpoint una ok  */
    @GetMapping("/user-id/{userId}")
    public ResponseEntity<?> findById(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(mpService.findById(userId));
    }

    /* endpoint modificar ok  */
    @PutMapping("/debitar")
    public ResponseEntity<?> debitar(@RequestBody MercadoCuentaDTO cuenta) {
            MercadoCuentaDTO actualizada = mpService.actualizarParada(cuenta);

            if(actualizada == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cuenta no encontrada");
            }
            return ResponseEntity.ok(actualizada);
    }

}
