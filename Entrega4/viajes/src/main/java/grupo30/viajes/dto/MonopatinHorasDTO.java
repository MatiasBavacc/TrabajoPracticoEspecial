package grupo30.viajes.dto;

public class MonopatinHorasDTO {
    private Long id_monopatin;
    private Double HorasEnViaje;   // 👈 mismo nombre que en el pipeline
    private Double horasPausa;

    public MonopatinHorasDTO(Long id_monopatin, Double HorasEnViaje, Double horasPausa) {
        this.id_monopatin = id_monopatin;
        this.HorasEnViaje = HorasEnViaje;
        this.horasPausa = horasPausa;
    }

    public Long getId_monopatin() { return id_monopatin; }
    public void setId_monopatin(Long id_monopatin) { this.id_monopatin = id_monopatin; }

    public Double getHorasEnViaje() { return HorasEnViaje; }
    public void setHorasEnViaje(Double HorasEnViaje) { this.HorasEnViaje = HorasEnViaje; }

    public Double getHorasPausa() { return horasPausa; }
    public void setHorasPausa(Double horasPausa) { this.horasPausa = horasPausa; }
}

