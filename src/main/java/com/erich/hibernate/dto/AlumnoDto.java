package com.erich.hibernate.dto;

import com.erich.hibernate.entity.Alumno;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AlumnoDto {

    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String direction;

    private List<CursoDto> cursos;

    private EmpresaDto empresa;

    public static AlumnoDto fromEntity(Alumno alumno) {
        if (alumno == null) {
            return null;
        }

        return AlumnoDto.builder().id(alumno.getId())
                .nombre(alumno.getNombre())
                .apellido(alumno.getApellido())
                .direction(alumno.getDirection())
                .cursos(alumno.getCursos() != null ? alumno.getCursos()
                        .stream()
                        .map(CursoDto::fromEntity)
                        .collect(Collectors.toList()) : null)
                .empresa(EmpresaDto.fromEntity(alumno.getEmpresa()))
                .build();
    }

    public static Alumno toEntity(AlumnoDto alumnoDto) {
        if (alumnoDto == null) {
            return null;
        }

        return Alumno.builder().id(alumnoDto.getId())
                .nombre(alumnoDto.getNombre())
                .apellido(alumnoDto.getApellido())
                .direction(alumnoDto.getDirection())
                .build();
    }
}
