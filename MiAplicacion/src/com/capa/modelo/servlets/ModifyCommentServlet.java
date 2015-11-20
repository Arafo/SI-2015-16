package com.capa.modelo.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
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
		int id = -1;
		String obra = null;
		String new_comment = null;

		if (request.getParameter("comment_id") != null)
			id = Integer.valueOf(request.getParameter("comment_id"));
		
		if (request.getParameter("obra_id") != null)
			obra = request.getParameter("obra_id");
		
		if (request.getParameter("new_comment") != null)
			new_comment = request.getParameter("new_comment");
				
		Facade f = new OracleConnector();
		
		// TODO Cambiar id de usuario por el real
		//int id_accion = f.insertAccion("modify_comentario", new Date(new java.util.Date().getTime()), f.getUser("Fallout1").getId());
		f.modifyComment(id, new_comment, new Date(new java.util.Date().getTime()));
		
		response.sendRedirect("obra.html?id=" + obra);
	}
}