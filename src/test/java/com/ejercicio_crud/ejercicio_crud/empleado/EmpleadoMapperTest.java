package com.ejercicio_crud.ejercicio_crud.empleado;

import com.ejercicio_crud.ejercicio_crud.empresa.Empresa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmpleadoMapperTest {
    private EmpleadoMapper empleadoMapper;

    @BeforeEach
    void setUp(){
        empleadoMapper = new EmpleadoMapper();
    }

    @Test
    void shouldMapEmpleadoDtoToEmpleado() {
        // Given
        EmpleadoDto dto = new EmpleadoDto(
                "Sharon",
                "Van",
                "Etten",
                "sharon@gmail.com",
                1
        );
        // When
        Empleado empleado = empleadoMapper.toEmpleado(dto);
        // Then
        Assertions.assertEquals(dto.nombre(), empleado.getNombre());
        Assertions.assertEquals(dto.apellidoPaterno(), empleado.getApelidoPaterno());
        Assertions.assertEquals(dto.apellidoMaterno(), empleado.getApellidoMaterno());
        Assertions.assertNotNull(dto.email(), empleado.getEmail());
        Assertions.assertEquals(dto.empresaId(), empleado.getEmpresa().getId());
    }

    @Test
    void shouldMapEmpleadoToEmpleadoResponseDto() {
        // Given
        Empleado empleado = new Empleado(
                "Lucia",
                "Rodriguez",
                "Zepeda",
                "lucia@gmail.com"
        );
        // When
        EmpleadoResponseDto responseDto = empleadoMapper.toEmpleadoResponseDto(empleado);
        // Then
        Assertions.assertEquals(responseDto.nombre(), empleado.getNombre());
        Assertions.assertEquals(responseDto.apellidoPaterno(), empleado.getApelidoPaterno());
        Assertions.assertEquals(responseDto.apellidoMaterno(), empleado.getApellidoMaterno());
        Assertions.assertNotNull(responseDto.email(), empleado.getEmail());
    }
}