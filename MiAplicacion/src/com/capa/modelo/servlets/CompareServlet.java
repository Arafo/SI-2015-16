package com.capa.modelo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa.modelo.Obra;
import com.capa.modelo.Persona;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;

public class CompareServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String obra1 = null;
		String obra2 = null;

		if (request.getParameter("obra1") != null)
			obra1 = request.getParameter("obra1");
		if (request.getParameter("obra2") != null)
			obra2 = request.getParameter("obra2");
		
		Facade f = new OracleConnector();
		Obra datosObra1 = f.getObra(obra1, "");
		Obra datosObra2 = f.getObra(obra2, "");
		List<Persona> personasObra1 = f.getPersonaTrabajo(f.getIdObra(obra1, ""));
		List<Persona> personasObra2 = f.getPersonaTrabajo(f.getIdObra(obra2, ""));
		
		request.setAttribute("obra1", datosObra1);
		request.setAttribute("obra2", datosObra2);
		request.setAttribute("personasObra1", personasObra1);
		request.setAttribute("personasObra2", personasObra2);

		RequestDispatcher view = request.getRequestDispatcher("comparacion.jsp");
		view.forward(request, response);
	}
}