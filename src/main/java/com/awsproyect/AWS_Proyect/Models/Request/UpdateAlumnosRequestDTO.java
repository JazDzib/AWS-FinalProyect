package com.awsproyect.AWS_Proyect.Models.Request;

import com.awsproyect.AWS_Proyect.Models.Alumno;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateAlumnosRequestDTO(
    String nombres,
    String apellidos,
    String matricula,
    Double promedio,
    String fotoPerfilUrl,
    String password
) {
    public Alumno entity(){
        return Alumno.builder()
                .nombres(this.nombres())
                .apellidos(this.apellidos())
                .matricula(this.matricula())
                .promedio(this.promedio())
                .fotoPerfilUrl(this.fotoPerfilUrl())
                .password(this.password())
                .build();

    }
}
