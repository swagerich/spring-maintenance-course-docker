package com.erich.hibernate.controller;

import com.erich.hibernate.dto.AlumnoDto;
import com.erich.hibernate.entity.Alumno;
import com.erich.hibernate.services.impl.AlumnoServiceImpl;

import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alumno")
public class AlumnoController {

    private final AlumnoServiceImpl alumnoService;

    public AlumnoController(AlumnoServiceImpl alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public AlumnoDto save(@Valid @RequestBody AlumnoDto alumnoDto) {
        return alumnoService.create(alumnoDto);
    }

    @PreAuthorize("hasRole({'ADMIN','USER'})")
    @GetMapping("/alumnos/{name}/{edad}")
    public List<Alumno> findAllNameAndEdad(@PathVariable String name, @PathVariable Integer edad) {
        return alumnoService.findByNameAndEdad(name, edad);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/alumno/{idCurso}")
    public AlumnoDto createAlumnoWithCurso(@RequestBody Alumno alumnoDto, @PathVariable Long idCurso) {
        return alumnoService.createAlumnoWithCourse(alumnoDto, idCurso);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/alcu/{id}")
    public AlumnoDto deleteAlumdnoWithCurso(@RequestBody Alumno alumnoDto, @PathVariable Long id) {
        return alumnoService.removeAlumnoWithCourse(alumnoDto, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public AlumnoDto update(@Valid @RequestBody AlumnoDto alumnoDto, @PathVariable Long id) {
        return alumnoService.update(alumnoDto, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        alumnoService.deleteAlumno(id);
    }

    @GetMapping("/alumnos")
    public List<AlumnoDto> findAll() {
        return alumnoService.findAll();
    }
}
