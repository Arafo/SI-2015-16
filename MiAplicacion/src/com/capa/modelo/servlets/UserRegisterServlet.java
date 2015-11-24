package com.capa.modelo.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import com.capa.modelo.Usuario;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;
import com.capa.persistencia.exceptions.EmailAlreadyExistsException;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int COOKIE_EXPIRETIME = 60 * 60 * 24;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> errors = new HashMap<String, String>();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String rePasswd = request.getParameter("password_confirmation");

		java.sql.Date birthDate = null;
		String address = request.getParameter("address");
		int telephone = 0;
		if (request.getParameter("tel") != null)
			telephone = request.getParameter("tel") != "" ? Integer.valueOf(request.getParameter("tel")) : 0;
		String sex = request.getParameter("sex");;
					
		SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
		try {
			if (request.getParameter("bday") != null)
				birthDate = new java.sql.Date(format.parse(request.getParameter("bday")).getTime());
		} catch (ParseException e1) {
			
		}
		
		// Los tres if's son siempre falso, los valores se comprueban en el jsp
		if (email == null || email == "") errors.put("Email", "Campo obligatorio");
		if (pass == null || pass == "") errors.put("Clave", "Campo obligatorio");
		else pass = encodeMd5(pass);
		if (rePasswd == null || rePasswd == "") errors.put("ReClave", "Campo obligatorio");
		else rePasswd = encodeMd5(rePasswd);

		
		if (!errors.isEmpty()) {
			// Forward a login.jsp con el mapa de errores
			request.setAttribute("errores", errors);
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
		}
		else {
			if (!pass.equals(rePasswd)) {
				errors.put("Clave", "Las contraseñas no coinciden"); // Forward a  Login.jsp
				request.setAttribute("errores", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
				dispatcher.forward(request, response);
			}
			else {
				Facade facade = new OracleConnector();
				Usuario user = new Usuario(name, sex, telephone, email, pass, birthDate, address);
				int err = 0;
				try {
					err = facade.insertUser(user);
					Cookie cookieLogin = new Cookie(CookieManager.COOKIENAME_USER, user.getEmail());
					Cookie cookieClave = new Cookie(CookieManager.COOKIENAME_PASS, user.getPass());
					cookieLogin.setMaxAge(COOKIE_EXPIRETIME);
					cookieClave.setMaxAge(COOKIE_EXPIRETIME);
					response.addCookie(cookieLogin);
					response.addCookie(cookieClave);			
					response.sendRedirect("home.html");
				} catch (EmailAlreadyExistsException e) {
					errors.put("Email", "Ya existe un usuario registrado con ese correo electrónico"); // Forward a  Login.jsp
					request.setAttribute("errores", errors);
					RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	}

	public static String encodeMd5(String input) {
		String md5 = null;

		if (null == input)
			return null;

		try {
			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {}
		
		return md5;
	}
}