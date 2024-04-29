package com.ejercicio_crud.ejercicio_crud.empleado;

import com.ejercicio_crud.ejercicio_crud.empresa.Empresa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Empleado {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotEmpty
    @Column(length=20)
    private String nombre;
    @Column(length=20)
    private String apelidoPaterno;
    @Column(length=20)
    private String apellidoMaterno;
    @Column(unique=true)
    private String email;

    @ManyToOne
    @JoinColumn(
            name="empresa_id"
    )
    @JsonBackReference
    private Empresa empresa;

    public Empleado() {

    }

    public Empleado(
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            String email){
        this.nombre = nombre;
        this.apelidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
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

    public String getApelidoPaterno() {
        return apelidoPaterno;
    }

    public void setApelidoPaterno(String apelidoPaterno) {
        this.apelidoPaterno = apelidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
