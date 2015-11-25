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


public class PaginationServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int recordsPerPage = 9;
		
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		
		Facade oc = new OracleConnector();
		// Se extraen de la BD las entradas necesarias
		List<Obra> list = oc.getObras((page - 1) * recordsPerPage, recordsPerPage);
		List<String> generos = oc.getGeneros(5);
		List<Obra> mejor_puntuadas = oc.getMejorPuntuadas(10);
		List<Obra> mas_comentadas = oc.getMasComentadas(10);
		int noOfRecords = oc.getNumObras();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		// Se muestran las entradas obtenidas
		request.setAttribute("obrasList", list);
		request.setAttribute("pages", noOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("generos", generos);
		request.setAttribute("mejor_puntuadas", mejor_puntuadas);
		request.setAttribute("mas_comentadas", mas_comentadas);
		
		RequestDispatcher view = request.getRequestDispatcher("home.jsp");
		view.forward(request, response);
	}
}