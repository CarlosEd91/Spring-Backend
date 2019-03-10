package com.tienda.online.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.online.models.Rol;
import com.tienda.online.repositories.RolRepository;

@Service
public class RolService {

	private RolRepository rolRepository;

	public RolService(RolRepository rolRepository) {
		super();
		this.rolRepository = rolRepository;
	}

	public Rol guardar(Rol rol) {
		if(rolRepository.findByRol(rol.getRol()) != null) {
			return null;
		}
		return rolRepository.save(rol);
	}
	
	public List<Rol> obtenerRoles(){
		return (List<Rol>) rolRepository.findAll();
	}
	
	public Rol actualizarRol(Rol rol) {
		return rolRepository.save(rol);
	}
	
	public void eliminarRol(Integer id) {
		rolRepository.deleteById(id);
	}
	
}
