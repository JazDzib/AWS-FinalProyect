package com.awsproyect.AWS_Proyect.Models.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public record VerifyRequestLoginDTO(
        @NotEmpty(message = "El session String o puede ser vacio")
        @JsonProperty("sessionString")
        String sessionString
) {
}
