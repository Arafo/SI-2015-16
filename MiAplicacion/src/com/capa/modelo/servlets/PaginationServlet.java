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
		List<Obra> list = oc.getObras((page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = oc.getNumObras();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
		request.setAttribute("obrasList", list);
		request.setAttribute("pages", noOfPages);
		request.setAttribute("currentPage", page);
		
		RequestDispatcher view = request.getRequestDispatcher("home.jsp");
		view.forward(request, response);
	}
}