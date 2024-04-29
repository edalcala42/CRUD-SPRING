package com.ejercicio_crud.ejercicio_crud.empleado;

import com.ejercicio_crud.ejercicio_crud.exceptions.ObjectNotValidException;
import com.ejercicio_crud.ejercicio_crud.handler.ApiError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/empleados")
    public EmpleadoResponseDto addEmpleado(
            @Valid @RequestBody EmpleadoDto dto
    ) {
        return this.empleadoService.addEmpleado(dto);
    }

    @GetMapping("/empleados")
    public List<EmpleadoResponseDto> getAllEmpleados(){
        return this.empleadoService.getAllEmpleados();
    }

    @GetMapping("/empleados/{empleado-id}")
    public EmpleadoResponseDto getEmpleadoById(
            @PathVariable("empleado-id") Integer empleadoId
    ){
        return this.empleadoService.getEmpleadoById(empleadoId);
    }

    @GetMapping("/empleados_nombres/{empleado-nombre}")
    public List<EmpleadoResponseDto> getEmpleadosWithName(
            @PathVariable("empleado-nombre") String empleadoNombre
    ){
        return this.empleadoService.getAllEmpleadosWithName(empleadoNombre);
    }

    @PutMapping("/empleados_actualizar/{empleado-id}")
    public EmpleadoResponseDto updateEmpleado(
            @PathVariable("empleado-id") Integer empleadoId,
            @RequestBody EmpleadoDto dto
    ){
        return this.empleadoService.updateEmpleadoById(empleadoId, dto);
    }

    @PatchMapping("empleado_actualizar_solo_algunos_campos/{empleado-id}")
    public EmpleadoResponseDto updateEmpleadoByCertainFieldsOnly(
            @PathVariable("empleado-id") Integer empleadoId,
            @RequestBody Map<String,Object> fields
    ){
        return this.empleadoService.updateEmpleadoByCertainFieldsOnly(empleadoId,fields);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/empleados/{empleado-id}")
    public void deleteEmpleadoById(
            @PathVariable("empleado-id") Integer empleadoId
    ){
        this.empleadoService.deleteEmpleadoById(empleadoId);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalException(IllegalStateException exception){
        ApiError error = new ApiError(
                400,
                "IllegalStateException. Asegúrate de incluir..." +
                        "nombre : String|| " +
                        "apellidoPaterno : String|| " +
                        "apellidoMaterno : String|| " +
                        "email : String|| " +
                        "empresaId : Integer|| ",
                new Date()
        );
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ApiError error = new ApiError(
                400,
                "MethodArgumentNotValidException. Asegúrate de incluir..." +
                        "nombre : String|| " +
                        "apellidoPaterno : String|| " +
                        "apellidoMaterno : String|| " +
                        "email : String|| " +
                        "empresaId : Integer|| ",
                new Date()
        );
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        ApiError error = new ApiError(
                400,
                "HttpMessageNotReadableException. Asegúrate de incluir..." +
                        "nombre : String|| " +
                        "apellidoPaterno : String|| " +
                        "apellidoMaterno : String|| " +
                        "email : String|| " +
                        "empresaId : Integer|| ",
                new Date()
        );
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(){
        ApiError error = new ApiError(
                400,
                "handleEntityNotFoundException. Asegúrate de incluir..." +
                        "nombre : String|| " +
                        "apellidoPaterno : String|| " +
                        "apellidoMaterno : String|| " +
                        "email : String|| " +
                        "empresaId : Integer|| ",
                new Date()
        );
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleObjectNotValidException(ObjectNotValidException exception){
        ApiError error = new ApiError(
                400,
                "ObjectNotValidException. Asegúrate de incluir..." +
                        "nombre : String|| " +
                        "apellidoPaterno : String|| " +
                        "apellidoMaterno : String|| " +
                        "email : String|| " +
                        "empresaId : Integer|| ",
                new Date()
        );
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException exception){
        ApiError error = new ApiError(
                400,
                "NullPointerException. Asegúrate de incluir..." +
                        "nombre : String|| " +
                        "apellidoPaterno : String|| " +
                        "apellidoMaterno : String|| " +
                        "email : String|| " +
                        "empresaId : Integer|| ",
                new Date()
        );
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exception){
        ApiError error = new ApiError(
                400,
                "No existe un empleado con ese Id",
                new Date()
        );
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleNoSuchElementException(DataIntegrityViolationException exception){
        ApiError error = new ApiError(
                400,
                "Se asoció el Id de una empresa inexistente ó el email está repetido",
                new Date()
        );
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleNoSuchElementException(ConstraintViolationException exception){
        ApiError error = new ApiError(
                400,
                "El campo de 'nombre' no debe estar vacío",
                new Date()
        );
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }


}
