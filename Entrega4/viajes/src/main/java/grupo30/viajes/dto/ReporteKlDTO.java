package grupo30.viajes.dto;

public class ReporteKlDTO {
    private Long id_monopatin;
    private Double kl_monopatin;

    public ReporteKlDTO(Long id_monopatin, Double kl_monopatin) {
        this.id_monopatin = id_monopatin;
        this.kl_monopatin = kl_monopatin;
    }

    public Long getId_monopatin() {
        return id_monopatin;
    }

    public void setId_monopatin(Long id_monopatin) {
        this.id_monopatin = id_monopatin;
    }

    public Double getKl_monopatin() {
        return kl_monopatin;
    }

    public void setKl_monopatin(Double kl_monopatin) {
        this.kl_monopatin = kl_monopatin;
    }
}
