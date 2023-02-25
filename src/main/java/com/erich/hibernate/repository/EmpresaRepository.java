package com.erich.hibernate.repository;

import com.erich.hibernate.entity.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepository extends CrudRepository<Empresa,Long> {
}
