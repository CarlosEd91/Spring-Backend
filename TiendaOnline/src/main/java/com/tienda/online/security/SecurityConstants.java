package com.tienda.online.security;

public class SecurityConstants {

	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/api/usuario";
	public static final String SIGN_IN_URL = "/api/login";
	public static final String ROL_URL = "/api/rol";
	
	public static final String PRODUCTO_URL = "/api/producto";

}
