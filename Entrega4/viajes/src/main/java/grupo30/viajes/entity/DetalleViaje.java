package grupo30.viajes.entity;

import grupo30.viajes.dto.NuevoDetalleDTO;

import java.time.LocalDateTime;

public class DetalleViaje {
    private LocalDateTime fechaHora;
    private Double kilometros;
    private Estado_viaje estado;

    public DetalleViaje() {
    }

    public DetalleViaje(LocalDateTime fechaHora, Double kilometros, Estado_viaje estado) {
        this.fechaHora = fechaHora;
        this.kilometros = kilometros;
        this.estado = estado;
    }

    public DetalleViaje(NuevoDetalleDTO dto) {
        this.fechaHora = LocalDateTime.now();
        this.kilometros = dto.getKilometros();
        this.estado = dto.getEstado();
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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

    @Override
    public String toString() {
        return "DetalleViaje{" +
                "fechaHora=" + fechaHora +
                ", kilometros=" + kilometros +
                ", estado=" + estado +
                '}';
    }
}
