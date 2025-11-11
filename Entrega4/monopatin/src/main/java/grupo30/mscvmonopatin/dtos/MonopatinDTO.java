package grupo30.mscvmonopatin.dtos;

import grupo30.mscvmonopatin.entity.EstadoMonopatin;
import grupo30.mscvmonopatin.entity.Monopatin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MonopatinDTO {
    private Long id;
    private EstadoMonopatin estado;
    private double latitud;
    private double longitud;
    private String patente;
    private Double km;
    private Double horas;

    public MonopatinDTO(Monopatin monopatin) {
        this.id = monopatin.getId();
        this.estado = monopatin.getEstadoMonopatin();
        this.latitud = monopatin.getLatitud();
        this.longitud = monopatin.getLongitud();
        this.km = monopatin.getKm();
        this.patente = monopatin.getPatente();
        this.horas = monopatin.getHoras();
    }
}
