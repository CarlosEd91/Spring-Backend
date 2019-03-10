package com.tienda.online.controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.online.dto.response.DataListResponse;
import com.tienda.online.dto.response.DataResponse;
import com.tienda.online.models.Usuario;
import com.tienda.online.services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController extends BaseController{

	private UsuarioService usuarioService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsuarioController(UsuarioService usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.usuarioService = usuarioService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping
	public DataResponse<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		Usuario newUsuario = usuarioService.guardar(usuario);
		if(newUsuario == null) {
			throw new DataIntegrityViolationException("Ya existe un usuario con email: " + usuario.getEmail());
		}
		return new DataResponse<Usuario>(newUsuario);
	}
	
	@GetMapping
	public DataListResponse<Usuario> obtenerUsuarios(){
		List<Usuario> lista = usuarioService.obtenerUsuarios();
		return new DataListResponse<>(lista, lista.size());
	}
	
	
	@PostMapping("obtenerUsuario")
	public DataResponse<Usuario> obtenerUsuario(@RequestBody Usuario usuario){
		return new DataResponse<>(usuarioService.obtenerUsuario(usuario));
	}
}
