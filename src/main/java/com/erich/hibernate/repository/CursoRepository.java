package com.erich.hibernate.repository;

import com.erich.hibernate.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso,Long> {
}
