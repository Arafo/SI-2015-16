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
		long startTime = System.nanoTime();
		
		if (request.getParameter("q") != null)
			obra = request.getParameter("q");
		
		Facade f = new OracleConnector();
		List<Obra> obrasList = f.getObrasSearch(obra);
		
		float endTime = (System.nanoTime() - startTime)/1e9f;
		request.setAttribute("obrasList", obrasList);
		request.setAttribute("obrasListSize", obrasList.size());
		request.setAttribute("time", endTime);

		request.setAttribute("query", obra);

		RequestDispatcher view = request.getRequestDispatcher("search.jsp");
		view.forward(request, response);
	}
}