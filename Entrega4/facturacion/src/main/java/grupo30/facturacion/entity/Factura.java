package grupo30.facturacion.entity;

import lombok.*;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;


@EnableFeignClients


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Document(collection = "facturas")
public class Factura {

    @Id
    private String id;
    private Usuario usuario;
    private Cuenta cuenta;
    private Viaje viaje;
    private LocalDate fecha;
    private String tipoFactura;
    private boolean paga;
    private double total;


    public Factura(Usuario u, Cuenta c, Viaje v) {
        this.usuario = u;
        this.cuenta = c;
        this.viaje = v;
        this.fecha = LocalDate.now();
        this.tipoFactura = "C";
        this.paga = false;
        this.total = this.calcularTotal(v);
    }

    private double calcularTotal(Viaje v) {
        double total = 0;
        //TODO: calcular
        return total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", cuenta=" + cuenta +
                ", viaje=" + viaje +
                ", fecha=" + fecha +
                ", tipoFactura='" + tipoFactura + '\'' +
                ", paga=" + paga +
                ", total=" + total +
                '}';
    }




}
