package com.capa.persistencia;

import java.sql.Date;

import com.capa.modelo.Usuario;

public class Pruebas {
	
	/**
	 * Metodo principal, para ejecutar y probar esta clase
	 * 
	 * @param args
	 *            Parametro siempre presente en el metodo main, en este caso no
	 *            se esta usando.
	 */
	public static void main (String[] args) {
		
		Facade q = new OracleConnector();

		try {
			q.insertUser(new Usuario(0, "s", "s", 1, "email", "s", new Date(new java.util.Date().getTime())));
		} catch (EmailAlreadyExistsException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(q.getUser("puta_caliente69@pene.com").getNombre());
		System.out.println(q.numObras());
	}

}
