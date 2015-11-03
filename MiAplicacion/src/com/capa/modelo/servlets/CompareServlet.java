package com.capa.modelo.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		System.out.println(obra1);
		System.out.println(obra2);
		request.setAttribute("obra1", obra1);
		request.setAttribute("obra2", obra2);


		RequestDispatcher view = request.getRequestDispatcher("comparacion.jsp");
		view.forward(request, response);
	}
}