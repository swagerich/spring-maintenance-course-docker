package com.erich.hibernate.services;

import com.erich.hibernate.dto.CursoDto;
import com.erich.hibernate.entity.Curso;

import java.util.List;

public interface CursoService {

    CursoDto create(CursoDto curso);

    List<CursoDto> findAll();

    CursoDto update(CursoDto curso,Long id);
}
