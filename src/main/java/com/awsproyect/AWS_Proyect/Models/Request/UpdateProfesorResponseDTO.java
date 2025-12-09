package com.awsproyect.AWS_Proyect.Models.Request;

import com.awsproyect.AWS_Proyect.Models.Profesor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateProfesorResponseDTO(
    String numeroEmpleado,
    String nombres,
    String apellidos,
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
