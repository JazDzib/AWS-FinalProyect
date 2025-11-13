package com.awsproyect.AWS_Proyect.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@Getter
@Setter
@Data
@EntityScan
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {

    private Long id;
    @NotEmpty(message = "el campo  'nombre' no debe estar vacio")
    private String nombres;
    @NotEmpty(message = "el campo 'apellido' no debe estar vacio")
    private String apellidos;
    @NotEmpty(message = "el campo 'matricula' no debe estar vacio")
    private String matricula;
    @NotNull(message = "el campo 'promedio' no debe ser nulo")
    @Min(value = 0, message = "El promedio no puede ser negativo")
    private Double promedio;
}
