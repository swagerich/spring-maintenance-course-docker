package com.erich.hibernate.repository;

import com.erich.hibernate.entity.auth.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository  extends CrudRepository<Role,Long> {

    Optional<Role> findByAuthority(String authority);
}
