package com.dms.dmsmoneyapi.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code TokenResource} é responsável em cancelar o cookie refreshToken no
 * cliente (lembrando que nossa API é STATELESS)
 * 
 * @author Diorgenes Morais
 * @version 1.0.0
 */
@RestController
@RequestMapping("/tokens")
public class TokenResource {

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath(request.getContextPath().concat("/oauth/token"));
		cookie.setMaxAge(0);

		response.addCookie(cookie);
		response.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
