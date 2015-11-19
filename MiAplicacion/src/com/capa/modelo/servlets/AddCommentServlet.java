package com.capa.modelo.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;

public class AddCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comentario = null;
		String obra = null;

		if (request.getParameter("comment") != null)
			comentario = request.getParameter("comment");
		
		if (request.getParameter("id") != null)
			obra = request.getParameter("id");
		
		
		Facade f = new OracleConnector();
		
		// TODO Cambiar id de usuario por el real
		int id_accion = f.insertAccion("comentario", new Date(new java.util.Date().getTime()), f.getUser("Fallout1").getId());
		f.insertComment(comentario, Integer.valueOf(obra), id_accion);

		response.sendRedirect("obra.html?id=" + obra);
	}
}