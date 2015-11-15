package com.capa.modelo.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		String email = request.getParameter("email");
		String pass = encodeMd5(request.getParameter("")); // TODO Coger pass
		String name = request.getParameter(""); // TODO Coger nombre
		String surname = request.getParameter(""); // TODO Coger apellidos
		SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
		java.sql.Date birthDate = null;
		try {
			birthDate = new java.sql.Date(format.parse(request.getParameter("")).getTime());
		} catch (ParseException e1) {}
		//String birthDate = request.getParameter("");
		String address = request.getParameter(""); // TODO Coger direccion
		int telephone = Integer.valueOf(request.getParameter("")); // TODO Coger telefono
		String sex = request.getParameter(""); // TODO Coger sexo
		String rePasswd = request.getParameter(""); // TODO Coger confirmacion

		boolean exito = true;

		if (!pass.equals(rePasswd)) {
			System.out.println("Las contraseñas no coinciden");
			exito = false;
		} // TODO errores de campos vacios para todas las entradas

		Facade facade = new OracleConnector(); // TODO facade

		String encodedUser = URLEncoder.encode(email, "utf-8");
		if (exito) {
			String encodedPswd = encodeMd5(pass);
			Usuario user = new Usuario(name, sex, telephone, email, pass, birthDate, surname, address);
			int err = 0;
			try {
				err = facade.insertUser(user);
			} catch (EmailAlreadyExistsException e) {
			}
//			switch (err) {
//			case 0:
//				Cookie cus = new Cookie(CookieManager.COOKIENAME_USER, email);
//				cus.setPath("/miAplicacion");
//				Cookie cpa = new Cookie(CookieManager.COOKIENAME_PASS, encodedPswd);
//				cpa.setPath("/miAplicacion");
//				response.addCookie(cpa);
//				response.addCookie(cus);
//				response.sendRedirect("/miAplicacion/showRegisterStatus.jsp?user=" + urlFormat(email)); // TODO revisar nombre .jsp
//				break;
//			case 1:
//				response.sendRedirect("/miAplicacion/showRegisterStatus.jsp?user=" + urlFormat(email) + "&error="
//						+ urlFormat("Email repetido. Inserte un email no existente")); // TODO revisar nombre .jsp
//				break;
//			default:
//				response.sendRedirect("/miAplicacion/showRegisterStatus.jsp?user=" + urlFormat(email) + "&error="
//						+ urlFormat("Error de conexion con la base de datos")); // TODO revisar nombre .jsp
//			}
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