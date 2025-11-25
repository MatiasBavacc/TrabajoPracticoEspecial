package grupo4.mscvusuario.dto;

import grupo4.mscvusuario.entity.Cuenta;
import grupo4.mscvusuario.entity.Rol;
import grupo4.mscvusuario.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private Boolean habilitado;
    private Rol rol;
    List<CuentaDTOAux> cuentas;

    public UsuarioDTO(Usuario u) {
        if(u == null) return;
        id = u.getId();
        usuario = u.getUsuario();
        nombre = u.getNombre();
        apellido = u.getApellido();
        email = u.getEmail();
        celular = u.getCelular();
        rol = u.getRol();
        habilitado = u.isHabilitado();
        cuentas = new java.util.ArrayList<>();
        for(Cuenta c: u.getCuentas()){
            cuentas.add(new CuentaDTOAux(c));
        }
    }
}
