package com.capa.modelo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
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
		int comentario = -1;
		String obra = null;

		if (request.getParameter("comment_id") != null)
			comentario = Integer.valueOf(request.getParameter("comment_id"));
		
		if (request.getParameter("id") != null)
			obra = request.getParameter("id");
				
		Facade f = new OracleConnector();
		
		// TODO Cambiar id de usuario por el real
		//int id_accion = f.insertAccion("delete_comentario", new Date(new java.util.Date().getTime()), f.getUser("Fallout1").getId());
		f.deleteComment(comentario);
		
		response.sendRedirect("obra.html?id=" + obra);
	}
}