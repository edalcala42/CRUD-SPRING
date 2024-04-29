package com.ejercicio_crud.ejercicio_crud.empresa;

import jakarta.validation.constraints.NotEmpty;

public record EmpresaDto(
        @NotEmpty(message = "Tienes que ingresar un nombre")
        String nombre
) {
}
