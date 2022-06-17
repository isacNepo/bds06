package com.devsuperior.movieflix.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
