package com.awsproyect.AWS_Proyect.Models.Request;

import com.awsproyect.AWS_Proyect.Models.Alumno;
import jakarta.validation.constraints.*;

public record UpdateAlumnosRequestDTO(
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "el 'nombre' no puede estar vacio")
    String nombres,
    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank(message = "el 'apellido' no puede estar vacio")
    String apellidos,
    @NotNull(message = "La matrícula no puede ser nula")
    String matricula,
    @Min(value = 0, message = "El promedio no puede ser negativo")
    @Max(value = 100,message = "El promedio no pude ser mayor a 100")
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
