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
		int obra1 = 0;
		int obra2 = 0;

		// Recoger los datos de las dos obras solicitadas para la busqueda
		if (request.getParameter("obra1") != null)
			obra1 = Integer.valueOf(request.getParameter("obra1"));
		if (request.getParameter("obra2") != null)
			obra2 = Integer.valueOf(request.getParameter("obra2"));
		
		Facade f = new OracleConnector();
		// Recoger los datos de las obras de la base de datos
		Obra datosObra1 = f.getObra(obra1);
		Obra datosObra2 = f.getObra(obra2);

		// Recoger los datos de las personas involucradas con las obras
		if (datosObra1 != null && datosObra2 != null) {
			List<Persona> personasObra1 = f.getPersonaTrabajo(obra1);
			List<Persona> personasObra2 = f.getPersonaTrabajo(obra2);
			
			int avg_obra1 = f.getUserAveragePuntuaciones(obra1);
			int avg_obra2 = f.getUserAveragePuntuaciones(obra2);
			
			// Preparar los datos recogidos
			request.setAttribute("obra1", datosObra1);
			request.setAttribute("obra2", datosObra2);
			request.setAttribute("personasObra1", personasObra1);
			request.setAttribute("personasObra2", personasObra2);
			request.setAttribute("avg_obra1", avg_obra1);
			request.setAttribute("avg_obra2", avg_obra2);

			
			// Mostrar los datos
			RequestDispatcher view = request.getRequestDispatcher("comparacion.jsp");
			view.forward(request, response);
		}
		else {
			response.sendRedirect("errors/error404.html");
		}
	}
}