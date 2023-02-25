package com.erich.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "alumnos")
public class Alumno extends AbstractEntity {

    private String nombre;

    private String apellido;

    private String direction;

    private Integer edad;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "alumnitos_cursitos",
            joinColumns = @JoinColumn(name = "alumnos_id"),
            inverseJoinColumns = @JoinColumn(name = "cursos_id"),
            foreignKey = @ForeignKey(name = "fk_alumnos_id"),
            inverseForeignKey = @ForeignKey(name = "fk_cursos_id"))
    private List<Curso> cursos = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_empresa_id"))
    private Empresa empresa;

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
        //curso.setAlumno(this);
    }

    public void removeCurso(Curso curso) {
        this.cursos.remove(curso);
        curso.setAlumno(null);
    }
}
