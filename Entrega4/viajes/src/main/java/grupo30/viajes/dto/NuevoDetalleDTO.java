package grupo30.viajes.dto;


import grupo30.viajes.entity.Estado_viaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


public class NuevoDetalleDTO {

    private Double kilometros;
    private Estado_viaje estado;

    public NuevoDetalleDTO(Double kilometros, Estado_viaje estado) {
        this.kilometros = kilometros;
        this.estado = estado;
    }

    public Double getKilometros() {
        return kilometros;
    }

    public void setKilometros(Double kilometros) {
        this.kilometros = kilometros;
    }

    public Estado_viaje getEstado() {
        return estado;
    }

    public void setEstado(Estado_viaje estado) {
        this.estado = estado;
    }
}
