package com.ejercicio_crud.ejercicio_crud.empresa;

import com.ejercicio_crud.ejercicio_crud.empleado.Empleado;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Empresa {
    @Id
    @GeneratedValue
    private int id;
    @Column(length=20)
    @NotNull(message = "El nombre no debe de ser nulo")
    @NotEmpty(message = "El nombre no debe de estar vacío")
    private String nombre;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            orphanRemoval = true,
            mappedBy = "empresa"
    )
    @JsonManagedReference
    private List<Empleado> empleados;

    public Empresa(){

    }

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
