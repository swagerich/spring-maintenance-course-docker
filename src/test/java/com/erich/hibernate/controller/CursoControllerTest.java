package com.erich.hibernate.controller;

import com.erich.hibernate.dto.CursoDto;

import com.erich.hibernate.entity.Curso;

import com.erich.hibernate.security.CustomUserDetails;
import com.erich.hibernate.security.jwt.JwtTokenProvider;
import com.erich.hibernate.services.impl.CursoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;

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

    @Test
    void create() {

    }

    @WithMockUser
    @Test
    void findAll() throws Exception {
        List<Curso> cursos = List.of(new Curso("JAVA", null),
                new Curso("ANGULAR", null));

        when(cursoService.findAll()).thenReturn(Streamable.of(cursos).stream().map(CursoDto::fromEntity).toList());
        mvc.perform(get("/api/v1/curso/cursos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(cursos)))
                .andExpect(jsonPath("$[0].nombre").value("JAVA"))
                .andExpect(jsonPath("$[1].nombre").value("ANGULAR"));

        verify(cursoService).findAll();
    }

    @Test
    void update() {
    }
}