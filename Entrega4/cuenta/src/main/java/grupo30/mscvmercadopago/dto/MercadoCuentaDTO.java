package grupo30.mscvmercadopago.dto;


import grupo30.mscvmercadopago.entity.MercadoCuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MercadoCuentaDTO {
    private String userId;
    private Double saldo;
    private Double moviento;

    public MercadoCuentaDTO(MercadoCuenta m){
        this.userId = m.getUserId();
        this.saldo = m.getSaldo();
        this.moviento = 0.0;
    }

}
