package grupo30.viajes.dto;

public class UsuarioViajesDTO {
    private Long id_usuario;
    private Integer cantidadViajes;

    public UsuarioViajesDTO(Long id_usuario, Integer cantidadViajes) {
        this.id_usuario = id_usuario;
        this.cantidadViajes = cantidadViajes;
    }

    public Long getId_usuario() { return id_usuario; }
    public void setId_usuario(Long id_usuario) { this.id_usuario = id_usuario; }

    public Integer getCantidadViajes() { return cantidadViajes; }
    public void setCantidadViajes(Integer cantidadViajes) { this.cantidadViajes = cantidadViajes; }
}
