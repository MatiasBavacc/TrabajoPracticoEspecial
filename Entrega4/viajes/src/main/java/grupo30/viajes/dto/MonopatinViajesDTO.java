package grupo30.viajes.dto;

public class MonopatinViajesDTO {

    private Long id_monopatin;
    private Integer cantidadViajes;

    public MonopatinViajesDTO(Long id_monopatin, Integer cantidadViajes) {
        this.id_monopatin = id_monopatin;
        this.cantidadViajes = cantidadViajes;
    }

    public Long getId_monopatin() { return id_monopatin; }
    public void setId_monopatin(Long id_monopatin) { this.id_monopatin = id_monopatin; }

    public Integer getCantidadViajes() { return cantidadViajes; }
    public void setCantidadViajes(Integer cantidadViajes) { this.cantidadViajes = cantidadViajes; }

}
