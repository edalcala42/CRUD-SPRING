package com.ejercicio_crud.ejercicio_crud.empresa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpresaMapperTest {
    private EmpresaMapper empresaMapper;
    @BeforeEach
    void setUp(){
        empresaMapper = new EmpresaMapper();
    }
    @Test
    void shouldMapEmpresaDtoToEmpresa() {
        // Given
        EmpresaDto empresaDto = new EmpresaDto(
                "Enterprise"
        );
        // When
        Empresa empresa = empresaMapper.toEmpresa(empresaDto);
        // Then
        Assertions.assertEquals(empresaDto.nombre(), empresa.getNombre());
    }

    @Test
    void shouldMapEmpresaToEmpresaResponseDto() {
        // Given
        Empresa empresa = new Empresa("Enterprise");
        // When
        EmpresaResponseDto empresaResponseDto = empresaMapper.toEmpresaResponseDto(empresa);
        // Then
        Assertions.assertEquals(empresa.getNombre(), empresaResponseDto.nombre());
    }
}