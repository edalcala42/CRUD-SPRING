package com.ejercicio_crud.ejercicio_crud.empresa;

import com.ejercicio_crud.ejercicio_crud.exceptions.ObjectNotValidException;
import com.ejercicio_crud.ejercicio_crud.handler.ApiError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping("/empresas")
    public EmpresaResponseDto addEmpresa(
            @RequestBody @Valid EmpresaDto dto
    ) {
        return empresaService.addEmpresa(dto);
    }

    @GetMapping("/empresas")
    public List<EmpresaResponseDto> getAllEmpresas(){
        return empresaService.getAllEmpresas();
    }

    @GetMapping("/empresas/{empresa-id}")
    public EmpresaResponseDto getEmpresaById(
            @PathVariable("empresa-id") Integer empresaId
    ){
        return empresaService.getEmpresaById(empresaId);
    }

    @PutMapping("/empresa_actualizar/{empresa-id}")
    public EmpresaResponseDto updateEmpresaById(
            @PathVariable("empresa-id") Integer empresaId,
            @RequestBody EmpresaDto dto
    ){
        return empresaService.updateByEmpresaId(empresaId, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/empresas/{empresa-id}")
    public void deleteEmpresaById(
            @PathVariable("empresa-id") Integer empresaId
    ){
        empresaService.deleteByEmpresaId(empresaId);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalException(IllegalStateException exception){
        ApiError error = new ApiError(400, "Formato incorrecto. Debe ser 'nombre':'valor'", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
        /*
        return ResponseEntity
                .badRequest().
                body(exception.getMessage());
         */
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ApiError error = new ApiError(400, "Argumento inválido. Debe ser 'nombre':'valor'", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
        /*
        return ResponseEntity
                .badRequest().
                body(exception.getMessage());
         */
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        ApiError error = new ApiError(400, "El formato del mensaje es incorrecto. Debe ser 'nombre':'valor'", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
        /*
        return ResponseEntity
                .badRequest().
                body(exception.getMessage());
         */
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(){
        ApiError error = new ApiError(400, "El formato de la entidad es incorrecto. Debe ser 'nombre':'valor' ", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
        /*
        return ResponseEntity
                .notFound().build();

         */
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleObjectNotValidException(ObjectNotValidException exception){
        ApiError error = new ApiError(400, "Objeto no válido", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
        /*
        return ResponseEntity
                .badRequest()
                .body(exception.getErrorMessages());
        */
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException exception){
        ApiError error = new ApiError(400, "Formato incorrecto. Debe ser 'nombre':'valor'", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
        /*return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
         */
    }
}
