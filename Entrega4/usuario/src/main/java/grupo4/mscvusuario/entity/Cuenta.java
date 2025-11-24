package grupo4.mscvusuario.entity;

import grupo4.mscvusuario.dto.CuentaDTO;
import grupo4.mscvusuario.dto.UsuarioDTOAux;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;

    @Enumerated(EnumType.STRING)
    private TipoDeCuenta tipoCuenta;
    @Column(name = "km_recorridos")
    private Double kmRecorridos;
    @Column(name = "fecua_limite_km")
    private LocalDate fecuaLimiteKm;

    @ManyToMany( mappedBy = "cuentas")
    private List<Usuario> usuarios;

    public Cuenta(CuentaDTO c){
        this.id = c.getId();
        this.monto = c.getMonto();
        this.tipoCuenta = c.getTipoCuenta();
        this.kmRecorridos = c.getKmRecorridos();
        this.fecuaLimiteKm = c.getFecuaLimiteKm();
        this.usuarios = new ArrayList<>();
        for( UsuarioDTOAux u : c.getUsuarios()){
            this.usuarios.add(new Usuario(u));
        }
    }
}
