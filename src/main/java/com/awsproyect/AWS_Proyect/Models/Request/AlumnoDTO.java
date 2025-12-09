package com.awsproyect.AWS_Proyect.Models.Request;

import com.awsproyect.AWS_Proyect.Models.Alumno;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record AlumnoDTO(
    @NotEmpty(message = "el campo  'nombre' no debe estar vacio")
    String nombres,
    @NotEmpty(message = "el campo 'apellido' no debe estar vacio")
    String apellidos,
    @NotEmpty(message = "el campo 'matricula' no debe estar vacio")
    String matricula,
    @NotNull(message = "el campo 'promedio' no debe ser nulo")
    @Min(value = 0, message = "El promedio no puede ser negativo")
    @Max(value = 100,message = "El promedio no pude ser mayor a 100")
    Double promedio,

    String fotoPerfilUrl,
    @NotEmpty(message = "el campo 'contraseña' no debe estar vacio")
    @Length(min = 0, message = "la contraseña debe ser minimo de 8 caracteres")
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
