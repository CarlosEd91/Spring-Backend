package com.tienda.online.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tienda.online.dto.response.UserDTO;
import com.tienda.online.models.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.tienda.online.security.SecurityConstants.EXPIRATION_TIME;
import static com.tienda.online.security.SecurityConstants.HEADER_STRING;
import static com.tienda.online.security.SecurityConstants.SECRET;
import static com.tienda.online.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(String loginUrl, AuthenticationManager authenticationManager) {
		super();
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginUrl, "POST"));
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			Usuario creds = new ObjectMapper().readValue(req.getInputStream(), Usuario.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		Usuario usuario = (Usuario) auth.getPrincipal();

		String token = Jwts.builder().setSubject(usuario.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

		UserDTO user = new UserDTO(usuario.getNombres() + " " + usuario.getApellidos(), usuario.getUsername(),
				TOKEN_PREFIX + token, usuario.getRol().getRol());

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonRespString = ow.writeValueAsString(user);

		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter().write(jsonRespString);
	}
}
