package grupo4.mscvmonopatin.dtos;

import grupo4.mscvmonopatin.entity.EstadoMonopatin;


// Los record por default sus atributos son public y final y tienen getters y setters
// Por eso no se necesitan los getters y setters y se puede usar solo el nombre del atributo
// Ademas pueden tener cosas null y eso sirve para los patch
public record MonopatinPatchDTO(
        EstadoMonopatin estado,
        Double latitud,
        Double longitud,
        Integer km)
{}
