package com.capa.modelo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;
import com.capa.persistencia.exceptions.InvalidPasswordException;
import com.capa.persistencia.exceptions.InvalidUserException;

public class DeleteUserDataServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = null;
		String pass = null;
		String id = null;
		String email = null;
		
		Cookie[] cookies = request.getCookies();
		// Se recogen los datos de la cookie de usuario
		for (int i = 0; i < cookies.length; i++) {
			String  nombreCookieI = cookies[i].getName(); 
			if (nombreCookieI.equals(CookieManager.COOKIENAME_USER))  
				user = cookies[i].getValue();
			if (nombreCookieI.equals(CookieManager.COOKIENAME_PASS)){  
				pass = cookies[i].getValue();
			}
		}
		
		// Recogida de los parametros
		if (request.getParameter("id") != null)
			id = request.getParameter("id");
		
		if (request.getParameter("email") != null)
			email = request.getParameter("email");
		
		Facade f = new OracleConnector();

		// Si el usuario esta logeado se permite el borrado
		try {
			if (user != null && pass != null && f.loginUser(user, pass) != null) {
				
				f.deleteUser(Integer.valueOf(id), email);
				
				HttpSession s = request.getSession();
				if (s != null) {
					CookieManager cm = new CookieManager(request, response);
					cm.deleteCookies(response);
					s.invalidate();
				}
				
				response.sendRedirect("home.html");
			}
			else {
				response.sendRedirect("member.html?=u" + email);
			}
		} catch (InvalidUserException e) {
			response.sendRedirect("member.html?=u" + email);
		} catch (InvalidPasswordException e) {
			response.sendRedirect("member.html?=u" + email);
		}

	}
}