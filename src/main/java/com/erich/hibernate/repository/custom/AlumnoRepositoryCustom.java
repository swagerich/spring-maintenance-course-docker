package com.erich.hibernate.repository.custom;

import com.erich.hibernate.dto.AlumnoDto;
import com.erich.hibernate.entity.Alumno;

import java.util.List;

public interface AlumnoRepositoryCustom {

    List<Alumno> findByNameAndEdad(String name, Integer edad);

    Long updteAlumnoIdByCourseId(Long alumnoId,Long cursoId);
}
