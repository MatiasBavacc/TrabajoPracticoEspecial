package grupo30.facturacion.client;

import grupo30.facturacion.dto.TarifaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "tarifas", url = "http://localhost:8086")
public interface TarifaFeignClient {


    @GetMapping("/vigente")
    ResponseEntity<TarifaDTO> findTarifaByFecha();

}

