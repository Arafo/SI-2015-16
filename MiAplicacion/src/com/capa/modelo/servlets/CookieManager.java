package com.capa.modelo.servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	public static final String COOKIENAME_USER = "loginUsuario";
	public static final String COOKIENAME_PASS = "claveUsuario";

	private Cookie username = null;
	private Cookie password = null;

	public CookieManager(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(COOKIENAME_USER)) {
					this.username = c;
				} else if (c.getName().equals(COOKIENAME_PASS)) {
					this.password = c;
				}
			}
		}
		// Cookies asignadas (Si las hay). Lo comprobamos
		if (this.username == null || this.password == null)
			deleteCookies(response);
		else {
			// |TODO Acceso a base de datos
			// Si falla deleteCookies()
		}
		// Si llegamos aqui con Cookies validas de usuario y password, somos un
		// usuario valido
	}

	/**
	 * Devuelve el nombre de usuario (Si estamos logueados). En caso contrario,
	 * null.
	 */
	public String getUsername() {
		if (this.username != null)
			return this.username.getValue();
		return null;
	}

	public void deleteCookies(HttpServletResponse response) {
		if (this.username != null) {
			this.username.setMaxAge(0);
			response.addCookie(this.username);
			this.username = null;
		}
		if (this.password != null) {
			this.password.setMaxAge(0);
			response.addCookie(this.password);
			this.password = null;
		}
	}
}