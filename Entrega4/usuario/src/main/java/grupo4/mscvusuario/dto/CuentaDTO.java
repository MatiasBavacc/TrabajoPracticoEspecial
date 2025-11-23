package grupo4.mscvusuario.dto;

import grupo4.mscvusuario.entity.Cuenta;
import grupo4.mscvusuario.entity.TipoDeCuenta;
import grupo4.mscvusuario.entity.Usuario;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class CuentaDTO {
    private Long id;
    private Double monto;

    private TipoDeCuenta tipoCuenta;
    private Double kmRecorridos;
    private LocalDate fecuaLimiteKm;
    private List<UsuarioDTOAux> usuarios;

    public CuentaDTO(Cuenta c){
        this.id = c.getId();
        this.monto = c.getMonto();
        this.tipoCuenta = c.getTipoCuenta();
        this.kmRecorridos = c.getKmRecorridos();
        this.fecuaLimiteKm = c.getFecuaLimiteKm();

        this.usuarios = new java.util.ArrayList<>();
        for(Usuario u: c.getUsuarios()) {
            this.usuarios.add(new UsuarioDTOAux(u));
        }

    }
}
