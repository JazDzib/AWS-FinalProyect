package com.awsproyect.AWS_Proyect.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@Getter
@Setter
@EntityScan
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    private Long id;
    @NotEmpty (message = "el campo 'numero empleado' no debe estar vacio")
    private String numeroEmpleado;
    @NotEmpty (message = "el campo 'nombres' no debe estar vacio")
    private String nombres;
    @NotEmpty(message = "el campo 'apellidos' no debe estar vacio")
    private String apellidos;
    @NotNull(message = "el campo 'horas' no debe ser nulo")
    @Min(value = 0, message = "no debe tener horas negativas")
    private Double horasClase;
}
