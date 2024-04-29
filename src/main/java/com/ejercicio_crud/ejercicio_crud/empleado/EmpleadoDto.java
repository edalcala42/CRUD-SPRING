package com.ejercicio_crud.ejercicio_crud.empleado;

import jakarta.validation.constraints.NotEmpty;

public record EmpleadoDto(
        @NotEmpty(message = "Tienes que ingresar un nombre")
        String nombre,
        @NotEmpty(message = "Tienes que ingresar un apellido paterno")
        String apellidoPaterno,
        String apellidoMaterno,
        @NotEmpty(message = "Tienes que ingresar un email")
        String email,
        Integer empresaId
) {
}
