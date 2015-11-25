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

public class DeleteCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = null;
		int comentario = -1;
		String obra = null;
		
		// Recoger los datos de la cookie actual
		Cookie[] cookies = request.getCookies(); 
		for (int i = 0; i < cookies.length; i++) {
			String  nombreCookieI = cookies[i].getName(); 
			if (nombreCookieI.equals(CookieManager.COOKIENAME_USER))  
				user = cookies[i].getValue();
		}
			
		// Recoger los datos del comentario y su obra asociada
		if (request.getParameter("comment_id") != null)
			comentario = Integer.valueOf(request.getParameter("comment_id"));
				
		if (request.getParameter("id") != null)
			obra = request.getParameter("id");
		
		Facade f = new OracleConnector();
	
		// Si el usuario logeado es el autor del comentario se acepta y ejecuta la peticion de borrado
		if (user != null && f.userDidComment(f.getUser(user).getId(), comentario)) {
			f.insertAccion("delete_comentario", new Date(new java.util.Date().getTime()), f.getUser(user).getId());
			f.deleteComment(comentario);
			
			response.sendRedirect("obra.html?id=" + obra);
		}
		else {
			response.sendRedirect("obra.html?id=" + obra);	
		}
	}
}