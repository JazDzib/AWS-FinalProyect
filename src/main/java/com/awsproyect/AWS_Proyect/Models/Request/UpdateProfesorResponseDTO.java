package com.awsproyect.AWS_Proyect.Models.Request;

import com.awsproyect.AWS_Proyect.Models.Profesor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateProfesorResponseDTO(
    @NotEmpty(message = "el campo 'numero de empleado' no debe estar vacio")
    String numeroEmpleado,
    @NotEmpty(message = "el campo 'nombres' no debe estar vacio")
    String nombres,
    @NotEmpty(message = "el campo 'apellidos' no debe estar vacio")
    String apellidos,
    @NotNull(message = "el campo 'horas' no debe ser nulo")
    @Min(value = 0, message = "no debe tener horas negativas")
    int horasClase
) {
    public Profesor entity(){
        return Profesor.builder()
                .numeroEmpleado(this.numeroEmpleado())
                .nombres(this.nombres())
                .apellidos(this.apellidos())
                .horasClase(this.horasClase())
                .build();
    }
}
