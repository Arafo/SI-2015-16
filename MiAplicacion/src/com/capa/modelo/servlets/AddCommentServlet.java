package com.capa.modelo.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;
import com.capa.persistencia.exceptions.InvalidPasswordException;
import com.capa.persistencia.exceptions.InvalidUserException;

public class AddCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = null;
		String pass = null;
		String comentario = null;
		String obra = null;
		int rating = 0;
		
		Cookie[] cookies = request.getCookies(); 
		for (int i = 0; i < cookies.length; i++) {
			String  nombreCookieI = cookies[i].getName(); 
			if (nombreCookieI.equals(CookieManager.COOKIENAME_USER))  
				user = cookies[i].getValue();
			if (nombreCookieI.equals(CookieManager.COOKIENAME_PASS)){  
				pass = cookies[i].getValue();
			}
		}
		
		Facade f = new OracleConnector();

		try {
			if (user != null && pass != null && f.loginUser(user, pass) != null) {
				if (request.getParameter("comment") != null)
					comentario = request.getParameter("comment");
				
				if (request.getParameter("id") != null)
					obra = request.getParameter("id");
				
				if (request.getParameter("rating") != null)
					rating = Integer.valueOf(request.getParameter("rating"));
				
				// TODO Cambiar id de usuario por el real
				int id_accion = f.insertAccion("comentario", new Date(new java.util.Date().getTime()), f.getUser(user).getId());
				f.insertComment(comentario, rating, Integer.valueOf(obra), id_accion);
				
				response.sendRedirect("obra.html?id=" + obra);
			}
			else {
				response.sendRedirect("obra.html?id=" + obra);
			}
		} catch (InvalidUserException e) {
			e.printStackTrace();
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		}

	}
}