package grupo4.facturacion.entity;

import lombok.*;
import org.springframework.cloud.openfeign.EnableFeignClients;


import java.time.LocalDate;
import java.util.Date;

@EnableFeignClients


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class Factura {
    private Long id;

    private Usuario usuario;
    private Cuenta cuenta;
    private Viaje viaje;
    private LocalDate fecha;
    private boolean paga;
    private Double Total;
    private Date fechaEmision;
    private double monto;
    private String estado; // Pagada o Pendiente de pago


    public Factura(String numeroFactura, String usuario, Date fechaEmision,
                   double monto, String estado, Long usuarioId) {
        this.numeroFactura = numeroFactura;
        this.usuario = usuario;
        this.fechaEmision = fechaEmision;
        this.monto = monto;
        this.estado = estado;
        this.usuarioId = usuarioId;
    }
}
