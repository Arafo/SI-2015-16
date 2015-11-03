package com.capa.modelo.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa.modelo.Obra;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;

public class ObraServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Facade f = new OracleConnector();
		
		int id = -1;
		if (request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id"));
		
		Obra obra = f.getObra(id);
		if (obra != null) {
			System.out.println(obra.getNombre());
			
			request.setAttribute("obra", obra);
			//request.setAttribute("pages", noOfPages);
			//request.setAttribute("currentPage", page);
			
			RequestDispatcher view = request.getRequestDispatcher("obra.jsp");
			view.forward(request, response);
		}
		else {
			// Error - Obra no encontrada
		}
	}
}