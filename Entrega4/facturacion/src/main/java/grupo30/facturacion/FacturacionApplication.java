package grupo30.facturacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "grupo30.facturacion.client")
public class FacturacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturacionApplication.class, args);
    }

}
