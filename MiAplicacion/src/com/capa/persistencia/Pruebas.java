package com.capa.persistencia;


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

		System.out.println(q.getUser("puta_caliente69@pene.com").getNombre());
		System.out.println(q.numObras());
	}

}
