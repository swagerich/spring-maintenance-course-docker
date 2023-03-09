package com.erich.hibernate.repository.custom.impl;

import com.erich.hibernate.entity.Alumno;
import com.erich.hibernate.entity.model.Alumno_;
import com.erich.hibernate.repository.custom.AlumnoRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.List;


public class AlumnoRepositoryCustomImpl implements AlumnoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Alumno> findByNameAndEdad(String nombre ,Integer edad) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Alumno> critera = criteriaBuilder.createQuery(Alumno.class);
        Root<Alumno> root = critera.from(Alumno.class);
        Predicate predicateName = criteriaBuilder.equal(root.get(Alumno_.NOMBRE), nombre);
        Predicate predicateEdad = criteriaBuilder.equal(root.get(Alumno_.EDAD), edad);
        critera.where(criteriaBuilder.or(predicateName,predicateEdad));

        TypedQuery<Alumno> query = entityManager.createQuery(critera);
        // Stream<AlumnoDto> stream = query.getResultStream();
        return query.getResultList();
    }


    @Override
    public Long updteAlumnoIdByCourseId(Long alumnoId, Long cursoId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Alumno> criteraUpdate = criteriaBuilder.createCriteriaUpdate(Alumno.class);
        Root<Alumno> root = criteraUpdate.from(Alumno.class);
        //FALTA IMPLEMENTAR UPDATE ALUMNO AND CURSO
        return null;
    }
}
