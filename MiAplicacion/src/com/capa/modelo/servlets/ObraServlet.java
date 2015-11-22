package com.capa.modelo.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa.modelo.Comentario;
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
			
			List<Comentario> comentarios = f.ObraComments(id);
			List<Obra> mejor_puntuadas = f.getMejorPuntuadas(5);
			List<Obra> mas_comentadas = f.getMasComentadas(5);
			obra.setPuntuacion(f.getUserAveragePuntuaciones(id));
			request.setAttribute("obra", obra);
			request.setAttribute("comentarios", comentarios);
			request.setAttribute("comentariosSize", comentarios.size());
			request.setAttribute("date_today", new Date());
			
			request.setAttribute("mejor_puntuadas", mejor_puntuadas);
			request.setAttribute("mas_comentadas", mas_comentadas);
			
			RequestDispatcher view = request.getRequestDispatcher("obra.jsp");
			view.forward(request, response);
		}
		else {
			// Error - Obra no encontrada
		}
	}
}