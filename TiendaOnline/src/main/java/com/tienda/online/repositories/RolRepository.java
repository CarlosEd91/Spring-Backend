package com.tienda.online.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tienda.online.models.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer>{

	Rol findByRol(String rol);
}
