package com.capa.modelo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa.modelo.Obra;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;

public class SearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String obra = null;
		String query = null;
		String genre = null;
		long startTime = System.nanoTime();
		
		// Recoger el parametro de busqueda
		if (request.getParameter("q") != null) {
			obra = request.getParameter("q");
			query = obra.trim().replaceAll("'", "''");
		}
		
		if (request.getParameter("genre") != null)
			genre = request.getParameter("genre");
				
		Facade f = new OracleConnector();
		List<Obra> obrasList;
		if (obra != null) 
			obrasList = f.getObrasSearch(query);
		else {
			obrasList = f.getObrasByGenero(genre);
			obra = "Genero: " + genre;
		}
		List<Obra> mejor_puntuadas = f.getMejorPuntuadas(5);
		List<Obra> mas_comentadas = f.getMasComentadas(5);
		
		// Extraer de la BD las entradas que cumplan la busqueda
		float endTime = (System.nanoTime() - startTime)/1e9f;
		request.setAttribute("obrasList", obrasList);
		request.setAttribute("obrasListSize", obrasList.size());
		request.setAttribute("time", endTime);
		request.setAttribute("mejor_puntuadas", mejor_puntuadas);
		request.setAttribute("mas_comentadas", mas_comentadas);
		request.setAttribute("query", obra);

		// Mostrar los resultados de la busqueda
		RequestDispatcher view = request.getRequestDispatcher("search.jsp");
		view.forward(request, response);
	}
}