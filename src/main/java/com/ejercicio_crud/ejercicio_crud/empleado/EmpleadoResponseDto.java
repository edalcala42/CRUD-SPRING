package com.ejercicio_crud.ejercicio_crud.empleado;

public record EmpleadoResponseDto(
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        String email
) {
}
