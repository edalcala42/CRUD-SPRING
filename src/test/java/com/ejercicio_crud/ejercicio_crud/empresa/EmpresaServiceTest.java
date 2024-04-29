package com.ejercicio_crud.ejercicio_crud.empresa;

import com.ejercicio_crud.ejercicio_crud.empleado.Empleado;
import com.ejercicio_crud.ejercicio_crud.empleado.EmpleadoResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmpresaServiceTest {
    @InjectMocks
    private EmpresaService empresaService;

    @Mock
    private EmpresaRepository empresaRepository;
    @Mock
    private EmpresaMapper empresaMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addEmpresa() {
        // Given
        EmpresaDto empresaDto = new EmpresaDto(
                "Enterprise"
        );
        Empresa empresa = new Empresa("Enterprise");
        Empresa savedEmpresa = new Empresa("Enterprise");
        // Mock calls
        Mockito.when(empresaMapper.toEmpresa(empresaDto)).thenReturn(empresa);
        Mockito.when(empresaRepository.save(empresa)).thenReturn(savedEmpresa);
        Mockito.when(empresaMapper.toEmpresaResponseDto(savedEmpresa)).thenReturn(
                new EmpresaResponseDto(
                        "Enterprise"
                )
        );
        // When
        EmpresaResponseDto empresaResponseDto = empresaService.addEmpresa(empresaDto);
        // Then
        assertEquals(empresaDto.nombre(), empresa.getNombre());
    }

    @Test
    void getAllEmpresas() {
        // Given
        List<Empresa> empresas = new ArrayList<>();
        empresas.add(
                new Empresa("Enterprise")
        );
        // Mock the calls
        Mockito.when(empresaRepository.findAll()).thenReturn(empresas);
        Mockito.when(empresaMapper.toEmpresaResponseDto(ArgumentMatchers.any(Empresa.class)))
                .thenReturn(
                        new EmpresaResponseDto(
                                "Enterprise"
                        )
                );
        // When
        List<EmpresaResponseDto> empresaResponseDtos = empresaService.getAllEmpresas();
        // Then
        assertEquals(empresas.size(), empresaResponseDtos.size());
    }

    @Test
    void getEmpresaById() {
        // Given
        Integer empresaId = 1;
        Empresa empresa = new Empresa("Enterprise");
        // Mock the calls
        Mockito.when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(empresa));
        Mockito.when(empresaMapper.toEmpresaResponseDto(ArgumentMatchers.any(Empresa.class)))
                .thenReturn(
                        new EmpresaResponseDto(
                                "Enterprise"
                        )
                );
        // When
        EmpresaResponseDto empresaResponseDto = empresaService.getEmpresaById(empresaId);
        // Then
        assertEquals(empresa.getNombre(), empresaResponseDto.nombre());
    }
}