package grupo30.viajes.dto;

public class CuentaViajesDTO {
    private Long numeroCuenta;
    private Integer cantidadViajes;

    public CuentaViajesDTO(Long numeroCuenta, Integer cantidadViajes) {
        this.numeroCuenta = numeroCuenta;
        this.cantidadViajes = cantidadViajes;
    }

    public Long getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(Long numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public Integer getCantidadViajes() { return cantidadViajes; }
    public void setCantidadViajes(Integer cantidadViajes) { this.cantidadViajes = cantidadViajes; }
}

