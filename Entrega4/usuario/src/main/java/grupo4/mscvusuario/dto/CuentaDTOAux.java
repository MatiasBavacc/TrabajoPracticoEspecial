package grupo4.mscvusuario.dto;

import grupo4.mscvusuario.entity.Cuenta;
import grupo4.mscvusuario.entity.TipoDeCuenta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CuentaDTOAux {
    private Long id;
    private Double monto;

    private TipoDeCuenta tipoCuenta;
    private Double kmRecorridos;
    private LocalDate fecuaLimiteKm;

    public CuentaDTOAux(Cuenta c){
        this.id = c.getId();
        this.monto = c.getMonto();
        this.tipoCuenta = c.getTipoCuenta();
        this.kmRecorridos = c.getKmRecorridos();
        this.fecuaLimiteKm = c.getFecuaLimiteKm();
    }
}
