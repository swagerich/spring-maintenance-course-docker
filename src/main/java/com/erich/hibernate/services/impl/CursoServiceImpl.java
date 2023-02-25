package com.erich.hibernate.services.impl;

import com.erich.hibernate.dto.CursoDto;
import com.erich.hibernate.entity.Curso;
import com.erich.hibernate.exception.NotFoundException;
import com.erich.hibernate.repository.CursoRepository;
import com.erich.hibernate.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepo;

    @Override
    @Transactional
    public CursoDto create(CursoDto cursoDto) {
        Curso curEntity = CursoDto.toEntity(cursoDto);
        return CursoDto.fromEntity(cursoRepo.save(curEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoDto> findAll() {
        return Streamable.of(cursoRepo.findAll())
                .stream()
                .map(CursoDto::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public CursoDto update(CursoDto cursoDto, Long id) {
        Curso cr = cursoRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado"));
        Curso curEntity = CursoDto.toEntity(cursoDto);
        cr.setNombre(curEntity.getNombre());
        return CursoDto.fromEntity(cursoRepo.save(cr));

    }
}
