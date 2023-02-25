package com.erich.hibernate.dto;

import com.erich.hibernate.entity.Curso;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
public class CursoDto {

    private Long id;

    @NotBlank
    private String nombre;

    private AlumnoDto alumno;

    public static CursoDto fromEntity(Curso curso){
        if(curso == null){
            return null;
        }
        return CursoDto.builder().id(curso.getId())
                .nombre(curso.getNombre())
                .alumno(AlumnoDto.fromEntity(curso.getAlumno()))
                .build();
    }

    public static Curso toEntity(CursoDto cursoDto){
        if(cursoDto == null){
            return null;
        }

        return Curso.builder().id(cursoDto.getId())
                .nombre(cursoDto.getNombre())
                .alumno(AlumnoDto.toEntity(cursoDto.getAlumno()))
                .build();
    }
}
