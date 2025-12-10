package com.awsproyect.AWS_Proyect.Models.Request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
    @NotEmpty(message = "la contraseña no puede esta vacia")
    String password
) {

}
