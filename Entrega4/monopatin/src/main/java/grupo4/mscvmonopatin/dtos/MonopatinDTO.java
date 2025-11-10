package grupo4.mscvmonopatin.dtos;

import grupo4.mscvmonopatin.entity.EstadoMonopatin;
import grupo4.mscvmonopatin.entity.Monopatin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MonopatinDTO {
    private EstadoMonopatin estado;
    private double latitud;
    private double longitud;
    private int kmRecorridos;

    public MonopatinDTO(Monopatin monopatin) {
        this.estado = monopatin.getEstadoMonopatin();
        this.latitud = monopatin.getLatitud();
        this.longitud = monopatin.getLongitud();
        this.kmRecorridos = monopatin.getKm();
    }
}
