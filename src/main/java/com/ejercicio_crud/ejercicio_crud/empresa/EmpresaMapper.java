package com.ejercicio_crud.ejercicio_crud.empresa;

import org.springframework.stereotype.Service;

@Service
public class EmpresaMapper {
    public Empresa toEmpresa(EmpresaDto dto){
        if(dto == null){
            throw new NullPointerException("El DTO de la empresa es nulo");
        }
        if(dto.nombre() == null){
            throw new NullPointerException("Se tiene que incluir un campo 'Nombre'");
        }
        var empresa = new Empresa();
        empresa.setNombre(dto.nombre());
        return empresa;
    }

    public EmpresaResponseDto toEmpresaResponseDto(Empresa empresa){
        if(empresa == null){
            throw new NullPointerException("La empresa se nula");
        }
        return new EmpresaResponseDto(empresa.getNombre());
    }
}
