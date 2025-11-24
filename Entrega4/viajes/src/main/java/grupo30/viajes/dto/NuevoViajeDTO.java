package grupo30.viajes.dto;

import grupo30.viajes.entity.DetalleViaje;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class NuevoViajeDTO {
    private Long usuario_id;
    private Long cuenta_id;
    private Long monopatin_id;
    private Long parada_inicio_id;
    private Double kl_monopatin;

    // Constructor con todos los atributos
    public NuevoViajeDTO(
                    Long usuario_id,
                    Long cuenta_id,
                    Long monopatin_id,
                    Long parada_inicio_id,
                    Double kl_monopatin) {
        this.usuario_id = usuario_id;
        this.cuenta_id = cuenta_id;
        this.monopatin_id = monopatin_id;
        this.parada_inicio_id = parada_inicio_id;
        this.kl_monopatin = kl_monopatin;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public Double getKl_monopatin() {
        return kl_monopatin;
    }

    public Long getMonopatin_id() {
        return monopatin_id;
    }

    public Long getParada_inicio_id() {
        return parada_inicio_id;
    }

    public Long getCuenta_id() {
        return cuenta_id;
    }
}