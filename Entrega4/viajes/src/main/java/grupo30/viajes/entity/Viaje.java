package grupo30.viajes.entity;

import grupo30.viajes.dto.NuevoViajeDTO;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.datatype.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "viajes")
public class Viaje {
    @Id
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

    // Constructor vacío (necesario para Spring Data)
    public Viaje() {
    }

    // Constructor con todos los atributos
    public Viaje(String id,
                 Long usuario_id,
                 Long cuenta_id,
                 Long monopatin_id,
                 Long parada_inicio_id,
                 Long parada_fin_id,
                 Double km_total_viaje,
                 Double horas_total_viaje,
                 Double horas_total_pausa,
                 Double kl_monopatin) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.cuenta_id = cuenta_id;
        this.monopatin_id = monopatin_id;
        this.parada_inicio_id = parada_inicio_id;
        this.parada_fin_id = parada_fin_id;
        this.km_total_viaje = km_total_viaje;
        this.horas_total_viaje = horas_total_viaje;
        this.horas_total_pausa = horas_total_pausa;
        this.detalles = new ArrayList<DetalleViaje>();
        this.detalles.add(new DetalleViaje(LocalDateTime.now(), kl_monopatin ,Estado_viaje.VIAJE));
    }

    // Constructor sin id (útil para crear nuevos viajes antes de persistir)
    public Viaje(Long usuario_id,
                 Long cuenta_id,
                 Long monopatin_id,
                 Long parada_inicio_id,
                 Long parada_fin_id,
                 Double km_total_viaje,
                 Double horas_total_viaje,
                 Double horas_total_pausa,
                 Double kl_monopatin) {
        this.usuario_id = usuario_id;
        this.cuenta_id = cuenta_id;
        this.monopatin_id = monopatin_id;
        this.parada_inicio_id = parada_inicio_id;
        this.parada_fin_id = parada_fin_id;
        this.km_total_viaje = km_total_viaje;
        this.horas_total_viaje = horas_total_viaje;
        this.horas_total_pausa = horas_total_pausa;
        this.detalles = new ArrayList<DetalleViaje>();
        this.detalles.add(new DetalleViaje(LocalDateTime.now(), kl_monopatin ,Estado_viaje.VIAJE));
    }

    public Viaje(NuevoViajeDTO dto) {
        this.usuario_id = dto.getUsuario_id();
        this.cuenta_id = dto.getCuenta_id();
        this.monopatin_id = dto.getMonopatin_id();
        this.parada_inicio_id = dto.getParada_inicio_id();
        this.parada_fin_id = null;
        this.km_total_viaje = 0.0;
        this.horas_total_viaje = 0.0;
        this.horas_total_pausa = 0.0;
        this.detalles = new ArrayList<DetalleViaje>();
        this.detalles.add(new DetalleViaje(LocalDateTime.now(), dto.getKl_monopatin() ,Estado_viaje.VIAJE));
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Long getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(Long cuenta_id) {
        this.cuenta_id = cuenta_id;
    }

    public Long getMonopatin_id() {
        return monopatin_id;
    }

    public void setMonopatin_id(Long monopatin_id) {
        this.monopatin_id = monopatin_id;
    }

    public Long getParada_inicio_id() {
        return parada_inicio_id;
    }

    public void setParada_inicio_id(Long parada_inicio_id) {
        this.parada_inicio_id = parada_inicio_id;
    }

    public Long getParada_fin_id() {
        return parada_fin_id;
    }

    public void setParada_fin_id(Long parada_fin_id) {
        this.parada_fin_id = parada_fin_id;
    }

    public Double getKm_total_viaje() {
        return km_total_viaje;
    }

    public void setKm_total_viaje(Double km_total_viaje) {
        this.km_total_viaje = km_total_viaje;
    }

    public Double getHoras_total_viaje() {
        return horas_total_viaje;
    }

    public void setHoras_total_viaje(Double horas_total_viaje) {
        this.horas_total_viaje = horas_total_viaje;
    }

    public Double getHoras_total_pausa() {
        return horas_total_pausa;
    }

    public void setHoras_total_pausa(Double horas_total_pausa) {
        this.horas_total_pausa = horas_total_pausa;
    }

    public List<DetalleViaje> getDetalles() {
        return new ArrayList<>(detalles);
    }

    public void setDetalles(List<DetalleViaje> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Viaje{" +
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

    public void addDetalle(DetalleViaje nuevo){
        DetalleViaje ultimo = this.getDetalles().getLast();
        if(!detalles.contains(nuevo) && ultimo.getFechaHora().isBefore(nuevo.getFechaHora())){
            switch (nuevo.getEstado()){
                case VIAJE:
                    //si el estado cambia a VIAJE termina una PAUSA
                    this.horas_total_pausa +=  this.sacarDiferenciaHoraria(ultimo.getFechaHora(),nuevo.getFechaHora());
                    break;
                case PAUSA:
                    //si el estado cambia a PAUSA estaba viajando
                    this.km_total_viaje += this.sacarDiferenciaKl(nuevo.getKilometros(), ultimo.getKilometros());
                    this.horas_total_viaje += this.sacarDiferenciaHoraria(ultimo.getFechaHora(),nuevo.getFechaHora());
                    break;
                case FINALIZADO:
                    //si finaliza el viaje, estaba VIAJANDO
                    this.km_total_viaje += this.sacarDiferenciaKl(nuevo.getKilometros(), ultimo.getKilometros());
                    this.horas_total_viaje += this.sacarDiferenciaHoraria(ultimo.getFechaHora(),nuevo.getFechaHora());
                    break;
            }
            this.detalles.add(nuevo);
        }

    }

    private Double sacarDiferenciaHoraria(LocalDateTime fechamenor, LocalDateTime fechamayor) {
        long minutos = ChronoUnit.MINUTES.between(fechamenor, fechamayor);
        return (double) minutos / 60;
    }


    private Double sacarDiferenciaKl(Double a, double b){
        return (a - b);
    }
}
