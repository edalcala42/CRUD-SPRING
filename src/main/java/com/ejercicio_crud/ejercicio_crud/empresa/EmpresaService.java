package com.ejercicio_crud.ejercicio_crud.empresa;

import com.ejercicio_crud.ejercicio_crud.empleado.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    public EmpresaService(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
    }

    public EmpresaResponseDto addEmpresa(EmpresaDto dto){
        var empresa = empresaMapper.toEmpresa(dto);
        var savedEmpresa = empresaRepository.save(empresa);
        return empresaMapper.toEmpresaResponseDto(savedEmpresa);
    }

    public List<EmpresaResponseDto> getAllEmpresas(){
        return empresaRepository.findAll()
                .stream()
                .map(empresaMapper::toEmpresaResponseDto)
                .collect(Collectors.toList());
    }

    public EmpresaResponseDto getEmpresaById(
            Integer empresaId
    ){
        return empresaRepository.findById(empresaId)
                .map(empresaMapper::toEmpresaResponseDto)
                .orElse(null);
    }

    public EmpresaResponseDto updateByEmpresaId(Integer empresaId, EmpresaDto empresaDto) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow();
        empresa.setNombre(empresaDto.nombre());
        Empresa savedEmpresa = empresaRepository.save(empresa);
        return empresaMapper.toEmpresaResponseDto(savedEmpresa);
    }

    public void deleteByEmpresaId(Integer empresaId){
        empresaRepository.deleteById(empresaId);
    }
}
