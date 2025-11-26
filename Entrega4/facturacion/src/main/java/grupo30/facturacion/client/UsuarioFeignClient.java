package grupo30.facturacion.client;


import grupo30.facturacion.entity.Cuenta;
import grupo30.facturacion.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios", url = "http://localhost:8087")
public interface UsuarioFeignClient {

    @GetMapping("/usuario/id/{id}")
    Usuario getUsuario(@PathVariable("id") Long id);

    @GetMapping("/cuenta/id/{id}")
    Cuenta getCuenta(@PathVariable("id") Long id);


}

