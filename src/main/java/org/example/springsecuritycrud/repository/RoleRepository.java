package org.example.springsecuritycrud.repository;

import org.example.springsecuritycrud.entities.Role;
import org.example.springsecuritycrud.entities.enums.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}