package com.tienda.online.dto.response;

public class UserDTO {
	
	private String nombres;
	private String email;
	private String token;
	private String rol;
	
	public UserDTO(String nombres, String email, String token, String rol) {
		super();
		this.nombres = nombres;
		this.email = email;
		this.token = token;
		this.rol = rol;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
