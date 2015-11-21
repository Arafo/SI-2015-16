package com.capa.modelo.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capa.modelo.Usuario;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;
import com.capa.persistencia.exceptions.InvalidPasswordException;
import com.capa.persistencia.exceptions.InvalidUserException;

public class UserLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int COOKIE_EXPIRETIME = 60 * 60 * 24;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Map<String, String> errors = new HashMap<String, String>();
		String email = request.getParameter("email");
		String password = encodeMd5(request.getParameter("password"));
		String remember = request.getParameter("remember");
		boolean exito = true;
		
		// Los tres if's son siempre falso, los valores se comprueban en el jsp
		if (email == null) errors.put("Login", "Campo obligatorio");
		if (password == null) errors.put("Clave", "Campo obligatorio");
		
		if (!errors.isEmpty()) {
			// Forward a login.jsp con el mapa de errores
			request.setAttribute("errores", errors);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			// Procesamiento del proceso de autenticacion
			Facade facade = new OracleConnector();
			try {
				Usuario user = facade.loginUser(email, password);
				
				HttpSession s = request.getSession();
				// TODO Cambiar a getNombre()
				s.setAttribute("nombre", user.getEmail());
				
				if (remember != null && remember.equals("on")) {
					Cookie cookieLogin = new Cookie(CookieManager.COOKIENAME_USER, user.getEmail());
					Cookie cookieClave = new Cookie(CookieManager.COOKIENAME_PASS, user.getPass());
					cookieLogin.setMaxAge(COOKIE_EXPIRETIME);
					cookieClave.setMaxAge(COOKIE_EXPIRETIME);
					response.addCookie(cookieLogin);
					response.addCookie(cookieClave);
				}
				response.sendRedirect("home.html");
			} catch (InvalidPasswordException e) {
				errors.put("Clave", "Clave de  acceso errónea"); // Forward a  Login.jsp
				request.setAttribute("errores", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			} catch (InvalidUserException e1) {
				errors.put("Login", "El usuario no se encuentra registrado"); // Forward a  Login.jsp
				request.setAttribute("errores", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
		
//		Usuario user = facade.getUser(email);
//		if (user == null)
//			exito = false;
//		else {
//			if (!pass.equals(user.getPass()))
//				exito = false;
//		}
//
//		String encodedUser = URLEncoder.encode(email, "UTF-8");
//		if (exito) {
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
//		}
//		response.sendRedirect("loginStatus.jsp?user=" + encodedUser + "&exito=" + exito);
	}

	/**
	 * Algoritmo de codificacion hash md5 para la contraseña. Con ello evitamos
	 * tener guardada la contraseña "como tal" en la base de datos (Y en la
	 * cookie), con el riesgo de seguridad que ello supondria. 
	 * Algoritmo obtenido de:
	 * http://viralpatel.net/blogs/java-md5-hashing-salting-password/
	 */
	private static String encodeMd5(String input) {
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