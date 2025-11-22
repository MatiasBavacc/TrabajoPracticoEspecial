package grupo4.mscvusuario.dto;

import grupo4.mscvusuario.entity.Cuenta;
import grupo4.mscvusuario.entity.Rol;
import jakarta.persistence.*;

import java.util.List;

public class UsuarioDTO {
    private Long id;
    private String usuario;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private Rol rol;
    List<Cuenta> cuentas;
}
