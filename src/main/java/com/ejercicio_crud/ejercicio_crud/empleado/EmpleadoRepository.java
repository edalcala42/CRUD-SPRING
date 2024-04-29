package com.ejercicio_crud.ejercicio_crud.empleado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    List<Empleado> findAllByNombreContaining(String nombre);
}
