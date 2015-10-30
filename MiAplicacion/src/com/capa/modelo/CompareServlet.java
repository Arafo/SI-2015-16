package com.capa.modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa.persistencia.OracleConnector;
import com.google.gson.Gson;

public class CompareServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		OracleConnector f = new OracleConnector();
		
		String name = request.getParameter("term");
		List<Obra> obras = f.getObras(name);

		String json = new Gson().toJson(obras);
		response.setContentType("application/json");

		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(json);
	}
}