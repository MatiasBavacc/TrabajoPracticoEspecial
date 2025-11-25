package grupo4.mscvusuario.controllers;

import grupo4.mscvusuario.dto.UsuarioDTO;
import grupo4.mscvusuario.entity.Usuario;
import grupo4.mscvusuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        List<UsuarioDTO> usuarios = usuarioService.findAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.ok(usuarios);
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        UsuarioDTO usuario = usuarioService.findById(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario u) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(u));
    }

    /* endpoint delete ok  */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        UsuarioDTO actual  = usuarioService.findById(id);
        usuarioService.delete(id);

        if(actual == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actual);
    }

    /* endpoint put ok  */
    @PutMapping("/modificar")
    public ResponseEntity<?> modificarUsuario(@RequestBody Usuario u) {
        UsuarioDTO actual = usuarioService.findById(u.getId());

        if(actual == null) {
            return ResponseEntity.notFound().build();
        }

        UsuarioDTO actualizado = usuarioService.update(u);
        return ResponseEntity.ok(actualizado);
    }

    // Nuevo endpoint para autenticación: busca por nombre de usuario (campo 'usuario')
    @GetMapping("/username")
    public ResponseEntity<UserAuthResponse> findByUsername(@RequestParam String username) {
        Usuario usuario = usuarioService.findEntityByUsuario(username);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserAuthResponse(usuario));
    }

    // DTO interno para Feign del gateway
    public record UserAuthResponse(Long id, String username, String password, String rol) {
        public UserAuthResponse(Usuario u) {
            this(u.getId(), u.getUsuario(), u.getPassword(), u.getRol().name());
        }
    }
}
