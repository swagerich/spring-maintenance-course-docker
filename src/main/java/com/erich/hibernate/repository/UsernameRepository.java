package com.erich.hibernate.repository;

import com.erich.hibernate.entity.auth.Username;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsernameRepository  extends CrudRepository<Username,Long> {

    Optional<Username> findByEmail(String email);

    Optional<Username> findByNombreOrEmail(String nombre,String email);

    Optional<Username> findByNombre(String nombre);

    Boolean existsByNombre(String nombre);

    Boolean existsByEmail(String email);
}

