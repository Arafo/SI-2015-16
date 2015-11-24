package com.capa.modelo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa.modelo.Accion;
import com.capa.modelo.Usuario;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;
import com.capa.persistencia.exceptions.InvalidPasswordException;
import com.capa.persistencia.exceptions.InvalidUserException;

public class MemberServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = null;
		String pass = null;
		
		String nombre = null;
		
		Cookie[] cookies = request.getCookies(); 
		for (int i = 0; i < cookies.length; i++) {
			String  nombreCookieI = cookies[i].getName(); 
			if (nombreCookieI.equals(CookieManager.COOKIENAME_USER))  
				user = cookies[i].getValue();
			if (nombreCookieI.equals(CookieManager.COOKIENAME_PASS)){  
				pass = cookies[i].getValue();
			}
		}
		
		Facade f = new OracleConnector();

		try {
			if (user != null && pass != null && f.loginUser(user, pass) != null) {
				if (request.getParameter("u") != null)
					nombre = request.getParameter("u");
						
				Usuario u = f.getUser(user);
				List<Accion> acciones = f.getAccionesUsuario(u.getId());
				
				request.setAttribute("datos", u);
				request.setAttribute("acciones", acciones);
				
				RequestDispatcher view = request.getRequestDispatcher("member.jsp?u=" + nombre);
				view.forward(request, response);
			}
			else {
				// Al no haber cookies, se hace logout al usuario
				response.sendRedirect("LogoutUsuario.do");
			}
		} catch (InvalidUserException e) {
			e.printStackTrace();
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		}
	}
}