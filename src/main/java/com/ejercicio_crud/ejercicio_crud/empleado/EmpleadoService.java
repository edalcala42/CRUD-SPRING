package com.ejercicio_crud.ejercicio_crud.empleado;

import com.ejercicio_crud.ejercicio_crud.empresa.Empresa;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {
    private final EmpleadoRepository repositorio;
    private final EmpleadoMapper empleadoMapper;

    public EmpleadoService(EmpleadoRepository repositorio, EmpleadoMapper empleadoMapper) {
        this.repositorio = repositorio;
        this.empleadoMapper = empleadoMapper;
    }

    public EmpleadoResponseDto addEmpleado(
            EmpleadoDto dto
    ){
        var empleado = empleadoMapper.toEmpleado(dto);
        var savedEmpleado = repositorio.save(empleado);
        return empleadoMapper.toEmpleadoResponseDto(savedEmpleado);
    }

    public List<EmpleadoResponseDto> getAllEmpleados(){
        return repositorio.findAll()
                .stream()
                .map(empleadoMapper::toEmpleadoResponseDto)
                .collect(Collectors.toList());
    }

    public EmpleadoResponseDto getEmpleadoById(Integer empleadoId){
        return repositorio.findById(empleadoId)
                .map(empleadoMapper::toEmpleadoResponseDto)
                .orElse(null);
    }

    public List<EmpleadoResponseDto> getAllEmpleadosWithName(String nombre){
        return repositorio.findAllByNombreContaining(nombre)
                .stream()
                .map(empleadoMapper::toEmpleadoResponseDto)
                .collect(Collectors.toList());
    }

    public EmpleadoResponseDto updateEmpleadoById(Integer empleadoId, EmpleadoDto empleadoDto){
        Empleado empleado = repositorio.findById(empleadoId).orElseThrow();
        empleado.setNombre(empleadoDto.nombre());
        empleado.setApelidoPaterno(empleadoDto.apellidoPaterno());
        empleado.setApellidoMaterno(empleadoDto.apellidoMaterno());
        empleado.setEmail(empleadoDto.email());
        var empresa = new Empresa();
        empresa.setId(empleadoDto.empresaId());
        empleado.setEmpresa(empresa);
        Empleado savedEmpleado = repositorio.save(empleado);
        return empleadoMapper.toEmpleadoResponseDto(savedEmpleado);
    }

    public EmpleadoResponseDto updateEmpleadoByCertainFieldsOnly(Integer empleadoId, Map<String, Object> fields){
        Empleado empleado = repositorio.findById(empleadoId).orElseThrow();
        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Empleado.class, key);
            if(key=="empresaId"){
                field = ReflectionUtils.findField(Empleado.class, "empresa");
            }
            if(field == null) return;
            field.setAccessible(true);
            if(key=="empresaId"){
                Empresa empresa = new Empresa();
                empresa.setId((Integer)value);
                ReflectionUtils.setField(field, empleado, empresa);
            }else{
                ReflectionUtils.setField(field, empleado, value);
            }
        });
        repositorio.save(empleado);
        return empleadoMapper.toEmpleadoResponseDto(empleado);
    }

    public void deleteEmpleadoById(Integer empleadoId){
        repositorio.deleteById(empleadoId);
    }
}
