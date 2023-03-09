package com.erich.hibernate.entity.model;

import com.erich.hibernate.entity.Alumno;
import com.erich.hibernate.entity.Curso;
import com.erich.hibernate.entity.Empresa;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Alumno.class)
public abstract class Alumno_ {

    public static volatile SingularAttribute<Alumno, Long> id;

    public static volatile SingularAttribute<Alumno, String> nombre;

    public static volatile SingularAttribute<Alumno, String> apellido;

    public static volatile SingularAttribute<Alumno, String> direction;

    public static volatile SingularAttribute<Alumno, Integer> edad;

    public static volatile ListAttribute<Alumno, Curso> cursos;

    public static volatile SingularAttribute<Alumno, Empresa> empresa;


    public static final String ID = "id";

    public static final String NOMBRE = "nombre";

    public static final String APELLIDO = "apellido";

    public static final String DIRECTION = "direction";

    public static final String EDAD = "edad";


}
