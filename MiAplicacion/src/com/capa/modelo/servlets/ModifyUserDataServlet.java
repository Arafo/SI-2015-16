package com.capa.modelo.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capa.modelo.Usuario;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;
import com.capa.persistencia.exceptions.EmailAlreadyExistsException;

public class ModifyUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int COOKIE_EXPIRETIME = 60 * 60 * 24;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> errors = new HashMap<String, String>();
				
		Facade facade = new OracleConnector();
		
		// Recogida de parametros desde el formulario
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String old_email = request.getParameter("old_email");
		Usuario old_user = facade.getUser(old_email);
		Date birthDate = null;
		String address = request.getParameter("address");
		int telephone = 0;
		if (request.getParameter("phone") != null) {
			try {
				telephone = request.getParameter("phone") != "" ? Integer.valueOf(request.getParameter("phone")) : 0;
			} catch (NumberFormatException e) {
				errors.put("Telefono", "Formato de teléfono no valido");
			}
		}
		String sex = request.getParameter("sex").toUpperCase();
		if (!sex.equals("H") && !sex.equals("M") && !sex.equals("U") &&
				!sex.equals("HOMBRE") && !sex.equals("MUJER") && !sex.equals("INDEFINIDO"))
			errors.put("Sexo", "Formato de sexo no válido");
		else {
			switch (sex) {
			case "HOMBRE": sex = "H"; break;
			case "MUJER": sex = "M"; break;
			case "INDEFINIDO": sex = "U"; break;
			}
		}
					
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (request.getParameter("bday") != null)
				birthDate = new Date(format.parse(request.getParameter("bday")).getTime());
		} catch (ParseException e1) {
			errors.put("Fecha", "Formato de fecha no valido (yyyy-MM-dd)");
		}
		
		// Comprobacion de que los datos recibidos no sean vacios
		if (email == null || email == "") errors.put("Email", "Campo obligatorio - Correo electrónico");

		// Si ha habido errores se redirecciona a las respuestas para los susodichos
		if (!errors.isEmpty()) {
			// Forward a member.jsp con el mapa de errores
			request.setAttribute("errores", errors);
			request.setAttribute("datos", old_user);
			request.setAttribute("acciones", facade.getAccionesUsuario(old_user.getId()));
			RequestDispatcher dispatcher = request.getRequestDispatcher("member.jsp?u=" + old_email);
			dispatcher.forward(request, response);
		}
		// Si no ha habido errores en los tipos de los parametros se procede a la modificacion
		else {

			// Si no hay errores de ningún tipo
			Usuario user = new Usuario(name, sex, telephone, email, old_user.getPass(), birthDate, address);

			try {
				// Se comprueba que se pueda modificar el usuario
				facade.modifyUser(user, old_user.getId());
				// Crear el HttpSession
				HttpSession s = request.getSession();
				if (s != null) {
					CookieManager cm = new CookieManager(request, response);
					cm.deleteCookies(response);
				}
				
				s.setAttribute("nombre", user.getEmail());
				// Se rellena la cookie con los datos de usuario
				Cookie cookieLogin = new Cookie(CookieManager.COOKIENAME_USER, user.getEmail());
				Cookie cookieClave = new Cookie(CookieManager.COOKIENAME_PASS, user.getPass());
				// Se limitan las cookies para que se anulen tras pasar el tiempo de expiracion
				cookieLogin.setMaxAge(COOKIE_EXPIRETIME);
				cookieClave.setMaxAge(COOKIE_EXPIRETIME);
				// Se añaden las cookies y se redirecciona a la pagina principal
				response.addCookie(cookieLogin);
				response.addCookie(cookieClave);			
				response.sendRedirect("member.html?u=" + email);
			} catch (EmailAlreadyExistsException e) {
				errors.put("Email", "Ya existe un usuario registrado con el correo electrónico " + email); // Forward a  Login.jsp
				request.setAttribute("errores", errors);
				request.setAttribute("datos", old_user);
				request.setAttribute("acciones", facade.getAccionesUsuario(old_user.getId()));
				RequestDispatcher dispatcher = request.getRequestDispatcher("member.jsp?u=" + old_email);
				dispatcher.forward(request, response);
			}
		}
	}
}