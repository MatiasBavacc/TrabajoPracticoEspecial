package grupo30.facturacion.entity;


import lombok.*;

import java.time.LocalDate;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Cuenta {
    private Long id;
    private Double monto;
    private TipoDeCuenta tipoCuenta;
    private Double kmRecorridos;
    private LocalDate fecuaLimiteKm;
    private List<Usuario> usuarios;
}
