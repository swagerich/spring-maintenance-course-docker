package com.erich.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "empresas")
public class Empresa extends AbstractEntity{

    private String nombre;

    private String ruc;

    private String direccion;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    private List<Alumno> alumnos = new ArrayList<>();

}
