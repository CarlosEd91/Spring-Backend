package com.tienda.online.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tienda.online.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

	Usuario findByEmail(String email);
	
	Usuario findByEmailAndPassword(String email, String password);
}
