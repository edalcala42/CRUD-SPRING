package com.ejercicio_crud.ejercicio_crud.empleado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoServiceTest {
    // Service to test
    @InjectMocks
    private EmpleadoService empleadoService;

    // Dependencies that the service uses
    @Mock
    private EmpleadoRepository empleadoRepository;
    @Mock
    private EmpleadoMapper empleadoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addEmpleado() {
        // Given
        EmpleadoDto dto = new EmpleadoDto(
                "Ruben",
                "Zepeda",
                "Sanchez",
                "ruben@gmail.com",
                1
        );
        Empleado empleado = new Empleado(
                "Ruben",
                "Zepeda",
                "Sanchez",
                "ruben@gmail.com"
        );
        Empleado savedEmpleado = new Empleado(
                "Ruben",
                "Zepeda",
                "Sanchez",
                "ruben@gmail.com"
        );

        // Mock the calls
        Mockito.when(empleadoMapper.toEmpleado(dto)).thenReturn(empleado);
        Mockito.when(empleadoRepository.save(empleado)).thenReturn(savedEmpleado);
        Mockito.when(empleadoMapper.toEmpleadoResponseDto(savedEmpleado)).thenReturn(
                new EmpleadoResponseDto(
                        "Ruben",
                        "Zepeda",
                        "Sanchez",
                        "ruben@gmail.com"
                )
        );

        // When
        EmpleadoResponseDto empleadoResponseDto = empleadoService.addEmpleado(dto);
        // Then
        assertEquals(dto.nombre(), empleadoResponseDto.nombre());
        assertEquals(dto.apellidoPaterno(), empleadoResponseDto.apellidoPaterno());
        assertEquals(dto.apellidoMaterno(), empleadoResponseDto.apellidoMaterno());
        assertEquals(dto.email(), empleadoResponseDto.email());

        // Verify methods from dependencies are only called the times they are supposed to
        Mockito.verify(empleadoMapper, Mockito.times(1)).toEmpleado(dto);
        Mockito.verify(empleadoRepository, Mockito.times(1)).save(empleado);
        Mockito.verify(empleadoMapper, Mockito.times(1)).toEmpleadoResponseDto(savedEmpleado);
    }

    @Test
    void getAllEmpleados() {
        // Given
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(
                new Empleado(
                        "Ruben",
                        "Zepeda",
                        "Sanchez",
                        "ruben@gmail.com"
                )
        );

        // Mock the calls
        Mockito.when(empleadoRepository.findAll()).thenReturn(empleados);
        Mockito.when(empleadoMapper.toEmpleadoResponseDto(ArgumentMatchers.any(Empleado.class)))
                .thenReturn(
                        new EmpleadoResponseDto(
                                "Ruben",
                                "Zepeda",
                                "Sanchez",
                                "ruben@gmail.com"
                        )
                );

        // When
        List<EmpleadoResponseDto> empleadoResponseDtos = empleadoService.getAllEmpleados();

        // Then
        assertEquals(empleados.size(), empleadoResponseDtos.size());

        // Verify methods from dependencies are only called the times they are supposed to
        Mockito.verify(empleadoRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getEmpleadoById() {
        // Given
        Integer empleadoId = 1;
        Empleado empleado = new Empleado(
                "Ruben",
                "Zepeda",
                "Sanchez",
                "ruben@gmail.com"
        );

        // Mock the calls
        Mockito.when(empleadoRepository.findById(empleadoId)).thenReturn(Optional.of(empleado));
        Mockito.when(empleadoMapper.toEmpleadoResponseDto(ArgumentMatchers.any(Empleado.class)))
                .thenReturn(
                        new EmpleadoResponseDto(
                                "Ruben",
                                "Zepeda",
                                "Sanchez",
                                "ruben@gmail.com"
                        )
                );
        // When
        EmpleadoResponseDto empleadoResponseDto = empleadoService.getEmpleadoById(empleadoId);

        // Then
        assertEquals(empleado.getNombre(), empleadoResponseDto.nombre());
        assertEquals(empleado.getApelidoPaterno(), empleadoResponseDto.apellidoPaterno());
        assertEquals(empleado.getApellidoMaterno(), empleadoResponseDto.apellidoMaterno());
        assertEquals(empleado.getEmail(), empleadoResponseDto.email());

        // Verify methods from dependencies are only called the times they are supposed to
        Mockito.verify(empleadoRepository, Mockito.times(1)).findById(empleadoId);
    }

    @Test
    void getAllEmpleadosWithName() {
        // Given
        String empleadoName = "Ru";
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(
                new Empleado(
                        "Ruben",
                        "Zepeda",
                        "Sanchez",
                        "ruben@gmail.com"
                )
        );

        // Mock the calls
        Mockito.when(empleadoRepository.findAllByNombreContaining(empleadoName)).thenReturn(empleados);
        Mockito.when(empleadoMapper.toEmpleadoResponseDto(ArgumentMatchers.any(Empleado.class)))
                .thenReturn(
                        new EmpleadoResponseDto(
                                "Ruben",
                                "Zepeda",
                                "Sanchez",
                                "ruben@gmail.com"
                        )
                );

        // When
        List<EmpleadoResponseDto> empleadoResponseDtos = empleadoService.getAllEmpleadosWithName(empleadoName);

        // Then
        assertEquals(empleados.size(), empleadoResponseDtos.size());

        // Verify methods from dependencies are only called the times they are supposed to
        Mockito.verify(empleadoRepository, Mockito.times(1)).findAllByNombreContaining(empleadoName);
    }
}