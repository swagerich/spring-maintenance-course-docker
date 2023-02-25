package com.erich.hibernate.repository;

import com.erich.hibernate.entity.Alumno;
import com.erich.hibernate.repository.custom.AlumnoRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno,Long>, AlumnoRepositoryCustom {


}
