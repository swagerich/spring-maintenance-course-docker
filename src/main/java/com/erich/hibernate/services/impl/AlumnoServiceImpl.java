package com.erich.hibernate.services.impl;

import com.erich.hibernate.dto.AlumnoDto;
import com.erich.hibernate.dto.CursoDto;
import com.erich.hibernate.entity.Alumno;
import com.erich.hibernate.entity.Curso;
import com.erich.hibernate.entity.Empresa;
import com.erich.hibernate.exception.NotFoundException;
import com.erich.hibernate.exception.ResourceException;
import com.erich.hibernate.repository.AlumnoRepository;
import com.erich.hibernate.repository.CursoRepository;
import com.erich.hibernate.services.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepo;
    private final CursoRepository cursoRepo;

    @Override
    @Transactional
    public AlumnoDto create(AlumnoDto alumnoDto) {
        Alumno alum = AlumnoDto.toEntity(alumnoDto);
        alum.setEmpresa(new Empresa("GOOGLE", "1231231", "av.washintong", null));
        return AlumnoDto.fromEntity(alumnoRepo.save(alum));
    }


    @Override
    public AlumnoDto update(AlumnoDto alumnoDto, Long id) {
        if (!alumnoRepo.existsById(id)) {
            throw new NotFoundException("ID NO EXISTE");
        }
        return alumnoRepo.findById(id).map(a -> {
            a.setNombre(alumnoDto.getNombre());
            a.setApellido(alumnoDto.getApellido());
            a.setDirection(alumnoDto.getDirection());
            return AlumnoDto.fromEntity(alumnoRepo.save(a));
        }).orElseThrow(() -> new ResourceException("No se pudo actualizar el alumno"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNameAndEdad(String name, Integer edad) {
        return alumnoRepo.findByNameAndEdad(name, edad);
    }

    @Override
    @Transactional
    public AlumnoDto createAlumnoWithCourse(Alumno alumno, Long idCourse) {
        Curso cursoId = cursoRepo.findById(idCourse).orElseThrow(() -> new NotFoundException("ID curso no encontrado"));
        alumno.setEmpresa(new Empresa("GOOGLE", "1231231", "av.washintong", null));
        alumno.addCurso(cursoId);
        return AlumnoDto.fromEntity(alumnoRepo.save(alumno));
    }

    @Override
    @Transactional
    public void deleteAlumno(Long id) {
        alumnoRepo.deleteById(id);
    }

    @Override
    @Transactional
    public AlumnoDto removeAlumnoWithCourse(Alumno alumno, Long idCourse) {
        Curso cursoId = cursoRepo.findById(idCourse).orElseThrow(() -> new NotFoundException("ID curso no encontrado"));
        alumno.removeCurso(cursoId);
        return AlumnoDto.fromEntity(alumnoRepo.save(alumno));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoDto> findAll() {
        return Streamable.of(alumnoRepo.findAll())
                .stream()
                .map(AlumnoDto::fromEntity)
                .toList();
    }
}
