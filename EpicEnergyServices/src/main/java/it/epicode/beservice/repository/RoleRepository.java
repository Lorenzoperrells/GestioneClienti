package it.epicode.beservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.RoleType;


public interface RoleRepository extends JpaRepository<Role,Long> {
	Optional<Role> findByRoletype(RoleType roletype);

}
