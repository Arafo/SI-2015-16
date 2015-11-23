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

public class ModifyCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = null;
		int id = -1;
		String obra = null;
		String new_comment = null;
		
		Cookie[] cookies = request.getCookies(); 
		for (int i = 0; i < cookies.length; i++) {
			String  nombreCookieI = cookies[i].getName(); 
			if (nombreCookieI.equals(CookieManager.COOKIENAME_USER))  
				user = cookies[i].getValue();
		}
		
		if (request.getParameter("comment_id") != null)
			id = Integer.valueOf(request.getParameter("comment_id"));
		
		if (request.getParameter("obra_id") != null)
			obra = request.getParameter("obra_id");
		
		if (request.getParameter("new_comment") != null)
			new_comment = request.getParameter("new_comment");
				
		Facade f = new OracleConnector();
		
		if (user != null && f.userDidComment(f.getUser(user).getId(), id)) {
			int id_accion = f.insertAccion("modify_comentario", new Date(new java.util.Date().getTime()), f.getUser(user).getId());
			f.modifyComment(id, new_comment, new Date(new java.util.Date().getTime()));
			
			response.sendRedirect("obra.html?id=" + obra);
		}
		else {
			response.sendRedirect("obra.html?id=" + obra);		
		}
	}
		
}