package grupo30.viajes.dto;

import grupo30.viajes.entity.DetalleViaje;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ViajeDTO {
    private String id;
    private Long usuario_id;
    private Long cuenta_id;
    private Long monopatin_id;
    private Long parada_inicio_id;
    private Long parada_fin_id;
    private List<DetalleViaje> detalles;
    private Double km_total_viaje;
    private Double horas_total_viaje;
    private Double horas_total_pausa;

    // Constructor con todos los atributos
    public ViajeDTO(String id,
                    Long usuario_id,
                    Long cuenta_id,
                    Long monopatin_id,
                    Long parada_inicio_id,
                    Long parada_fin_id,
                    Double km_total_viaje,
                    Double horas_total_viaje,
                    Double horas_total_pausa,
                    List<DetalleViaje> detalles) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.cuenta_id = cuenta_id;
        this.monopatin_id = monopatin_id;
        this.parada_inicio_id = parada_inicio_id;
        this.parada_fin_id = parada_fin_id;
        this.km_total_viaje = km_total_viaje;
        this.horas_total_viaje = horas_total_viaje;
        this.horas_total_pausa = horas_total_pausa;
        this.detalles = detalles;
    }

    // Constructor sin id (útil para crear nuevos viajes antes de persistir)
    public ViajeDTO(Long usuario_id,
                    Long cuenta_id,
                    Long monopatin_id,
                    Long parada_inicio_id,
                    Long parada_fin_id,
                    Double km_total_viaje,
                    Double horas_total_viaje,
                    Double horas_total_pausa,
                    List<DetalleViaje> detalles) {
        this.usuario_id = usuario_id;
        this.cuenta_id = cuenta_id;
        this.monopatin_id = monopatin_id;
        this.parada_inicio_id = parada_inicio_id;
        this.parada_fin_id = parada_fin_id;
        this.km_total_viaje = km_total_viaje;
        this.horas_total_viaje = horas_total_viaje;
        this.horas_total_pausa = horas_total_pausa;
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "ViajeDTO{" +
                "id='" + id + '\'' +
                ", usuario_id=" + usuario_id +
                ", cuenta_id=" + cuenta_id +
                ", monopatin_id=" + monopatin_id +
                ", parada_inicio_id=" + parada_inicio_id +
                ", parada_fin_id=" + parada_fin_id +
                ", km_total_viaje=" + km_total_viaje +
                ", horas_total_viaje=" + horas_total_viaje +
                ", horas_total_pausa=" + horas_total_pausa +
                '}';
    }

    public String getId() { return id; }
    public Long getUsuario_id() { return usuario_id; }
    public Long getCuenta_id() { return cuenta_id; }
    public Long getMonopatin_id() { return monopatin_id; }
    public Long getParada_inicio_id() { return parada_inicio_id; }
    public Long getParada_fin_id() { return parada_fin_id; }
    public Double getKm_total_viaje() { return km_total_viaje; }
    public Double getHoras_total_viaje() { return horas_total_viaje; }
    public Double getHoras_total_pausa() { return horas_total_pausa; }
    public List<DetalleViaje> getDetalles() {
        return detalles;
    }
}