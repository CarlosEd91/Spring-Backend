package com.tienda.online.services;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tienda.online.models.Usuario;
import com.tienda.online.repositories.RolRepository;
import com.tienda.online.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	private UsuarioRepository usuarioRepository;
	private RolRepository rolRepository;

	public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.rolRepository = rolRepository;
	}
	
	public Usuario guardar(Usuario usuario) {
		usuario.setFecha(new Date());
		Usuario usurioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if(usurioExistente == null) {
			Usuario usuarioSaved =  usuarioRepository.save(usuario);
			usuarioSaved.setRol(rolRepository.findById(usuario.getRol().getId()).get());
			return usuarioSaved;
		}
		return null;
	}
	
	public List<Usuario> obtenerUsuarios(){
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
	public Usuario obtenerUsuario(Usuario usuario){
		return usuarioRepository.findByEmailAndPassword
				(usuario.getEmail(), usuario.getPassword());
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario applicatioUser = usuarioRepository.findByEmail(email);
		if(applicatioUser == null) {
			throw new UsernameNotFoundException(email);
		}
		return applicatioUser;
	}
}
