package com.erich.hibernate.controller;

import com.erich.hibernate.dto.CursoDto;
import com.erich.hibernate.services.impl.CursoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curso")
public class CursoController {

    private final CursoServiceImpl cursoService;

    public CursoController(CursoServiceImpl cursoService) {
        this.cursoService = cursoService;
    }

    @PreAuthorize("hasAnyRole({'ADMIN','USER'})")
    @PostMapping
    public CursoDto create(@Valid @RequestBody CursoDto cursoDto){
        return cursoService.create(cursoDto);
    }

    @GetMapping("/cursos")
    public List<CursoDto> findAll(){
        return cursoService.findAll();
    }

    @PreAuthorize("hasAnyRole({'ADMIN','USER'})")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CursoDto update(@Valid @RequestBody CursoDto cursoDto,@PathVariable Long id){
        return cursoService.update(cursoDto,id);
    }
}
