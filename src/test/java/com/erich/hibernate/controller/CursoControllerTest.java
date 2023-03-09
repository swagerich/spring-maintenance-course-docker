package com.erich.hibernate.controller;

import com.erich.hibernate.dto.CursoDto;

import com.erich.hibernate.entity.Curso;

import com.erich.hibernate.security.CustomUserDetails;
import com.erich.hibernate.security.jwt.JwtTokenProvider;
import com.erich.hibernate.services.impl.CursoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.util.Streamable;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@WebMvcTest(value = CursoController.class,includeFilters = {
      @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = JwtTokenProvider.class)
})
class CursoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CursoServiceImpl cursoService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    ObjectMapper objectMapper;

    @MockBean
    CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }


    @WithMockUser(value = "USER",authorities = {"USER","ADMIN"})
    @Test
    void create() throws Exception {

        Curso curso = new Curso();
        curso.setId(null);
        curso.setAlumno(null);
        curso.setNombre("REACT");
        curso.setCreationDate(Instant.now());
        curso.setLastModifiedDate(Instant.now());

        CursoDto cursoDto  = CursoDto.fromEntity(curso);
        when(cursoService.create(any())).then(i ->{
            Curso c = i.getArgument(0);
            c.setId(1L);
            return c;
        });
        mvc.perform(post(("/api/v1/curso")).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cursoDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("REACT"));

        verify(cursoService.create(any()));

    }

    @WithMockUser(value = "USER",roles = {"USER","ADMIN"})
    @Test
    void findAll() throws Exception {

        List<Curso> cursos  = new ArrayList<>();
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNombre("JAVA");
        curso.setAlumno(null);
        curso.setCreationDate(Instant.now());
        curso.setLastModifiedDate(null);

        Curso curso2 = new Curso();
        curso2.setId(2L);
        curso2.setNombre("ANGULAR");
        curso2.setAlumno(null);
        curso2.setCreationDate(Instant.now());
        curso2.setLastModifiedDate(null);

        cursos.addAll(Arrays.asList(curso,curso2));

        when(cursoService.findAll()).thenReturn(Streamable.of(cursos).stream().map(CursoDto::fromEntity).toList());
        mvc.perform(get("/api/v1/curso/cursos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(cursos)))
                .andExpect(jsonPath("$[0].nombre").value("JAVA"))
                .andExpect(jsonPath("$[1].nombre").value("ANGULAR"));

        verify(cursoService).findAll();
    }

    @WithMockUser(value = "USER",roles = {"USER","ADMIN"})
    @Test
    void update() throws Exception {
        Curso curso = new Curso();
        curso.setId(null);
        curso.setAlumno(null);
        curso.setNombre("ANGULAR");
        curso.setCreationDate(Instant.now());
        curso.setLastModifiedDate(Instant.now());

        CursoDto cursoDto  = CursoDto.fromEntity(curso);

        when(cursoService.update(any(CursoDto.class),anyLong())).thenReturn(cursoDto);

        mvc.perform(put("/api/v1/curso/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", Matchers.is("ANGULAR")));

        verify(cursoService).update(any(),anyLong());

    }
}