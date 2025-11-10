package grupo30.paradas.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstacionarDTO {
    private Long monopatinId;

    public EstacionarDTO(Long monopatinId) {
        this.monopatinId = monopatinId;
    }
}
