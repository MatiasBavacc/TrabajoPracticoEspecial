package grupo4.mscvusuario.dto;

import grupo4.mscvusuario.entity.Cuenta;
import grupo4.mscvusuario.entity.Rol;
import grupo4.mscvusuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String usuario;
    private String nombre;
    private String email;
    private String celular;
    private Boolean estado;
    private Rol rol;
    List<Cuenta> cuentas;

    public UsuarioDTO(Usuario u) {
        id = u.getId();
        usuario = u.getUsuario();
        nombre = u.getUsuario();
        email= u.getEmail();
        celular = u.getCelular();
        rol = u.getRol();
        estado = u.isHabilitado();
        cuentas = u.getCuentas() ;
    }

}

