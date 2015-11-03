package com.capa.modelo.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.net.CookieManager;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa.modelo.Usuario;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;

public class UserLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int COOKIE_EXPIRETIME = 60 * 60 * 24;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String pass = encodeMd5(request.getParameter(""));
		String stayLogged = request.getParameter("");
		boolean exito = true;

		Facade facade = new OracleConnector();
		Usuario user = facade.getUser(email);
		if (user == null)
			exito = false;
		else {
			if (!pass.equals(user.getPass()))
				exito = false;
		}

		String encodedUser = URLEncoder.encode(email, "UTF-8");
		if (exito) {
//			Cookie cUser = new Cookie(CookieManager.COOKIENAME_USER, user.getEmail());
//			cUser.setPath("/miAplicacion");
//			Cookie cPass = new Cookie(CookieManager.COOKIENAME_PASS, pass);
//			cPass.setPath("/miAplicacion");
//			if (mantener != null && mantener.equals("m")) {
//				cUser.setMaxAge(COOKIE_EXPIRETIME);
//				cPass.setMaxAge(COOKIE_EXPIRETIME);
//			}
//			response.addCookie(cUser);
//			response.addCookie(cPass);
		}
		response.sendRedirect("loginStatus.jsp?user=" + encodedUser + "&exito=" + exito);
	}

	/**
	 * Algoritmo de codificacion hash md5 para la contraseña. Con ello evitamos
	 * tener guardada la contraseña "como tal" en la base de datos (Y en la
	 * cookie), con el riesgo de seguridad que ello supondria. 
	 * Algoritmo obtenido de:
	 * http://viralpatel.net/blogs/java-md5-hashing-salting-password/
	 */
	public static String encodeMd5(String input) {
		String md5 = null;
		if (null == input)
			return null;

		try {

			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

		}
		return md5;
	}
}