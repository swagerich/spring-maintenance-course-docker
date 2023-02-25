package com.erich.hibernate.services;

import com.erich.hibernate.dto.AlumnoDto;
import com.erich.hibernate.entity.Alumno;
import com.erich.hibernate.entity.Curso;
import org.apache.catalina.User;

import java.util.List;

public interface AlumnoService {

    AlumnoDto create(AlumnoDto alumno);

    AlumnoDto update(AlumnoDto alumno,Long id);

    AlumnoDto createAlumnoWithCourse(Alumno alumno,Long idCourse);

    List<Alumno> findByNameAndEdad(String name, Integer edad);

    AlumnoDto removeAlumnoWithCourse(Alumno alumno, Long idCourse);

    void deleteAlumno(Long id);
    List<AlumnoDto> findAll();
}
