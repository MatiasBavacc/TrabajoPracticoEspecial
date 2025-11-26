package grupo30.facturacion.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long id;
    private String usuario;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private Rol rol;
    private boolean habilitado;
    private Cuenta cuenta;


}
