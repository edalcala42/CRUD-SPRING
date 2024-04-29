package com.ejercicio_crud.ejercicio_crud.empleado;

import com.ejercicio_crud.ejercicio_crud.empresa.Empresa;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoMapper {
    public Empleado toEmpleado(EmpleadoDto dto){
        if(dto == null){
            throw new NullPointerException("El DTO de empleado es nulo");
        }
        var empleado = new Empleado();
        empleado.setNombre(dto.nombre());
        empleado.setApelidoPaterno(dto.apellidoPaterno());
        empleado.setApellidoMaterno(dto.apellidoMaterno());
        empleado.setEmail(dto.email());

        var empresa = new Empresa();
        empresa.setId(dto.empresaId());
        empleado.setEmpresa(empresa);

        return empleado;
    }

    public EmpleadoResponseDto toEmpleadoResponseDto(Empleado empleado){
        if(empleado == null){
            throw new NullPointerException("El empleado es nulo");
        }
        return new EmpleadoResponseDto(empleado.getNombre(), empleado.getApelidoPaterno(), empleado.getApellidoMaterno(), empleado.getEmail());
    }
}
