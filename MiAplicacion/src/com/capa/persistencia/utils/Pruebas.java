package com.capa.persistencia.utils;

import java.sql.Date;
import java.util.List;

import com.capa.modelo.Obra;
import com.capa.modelo.Persona;
import com.capa.modelo.Usuario;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;
import com.capa.persistencia.exceptions.EmailAlreadyExistsException;

public class Pruebas {
	
	/**
	 * Metodo principal, para ejecutar y probar esta clase
	 * 
	 * @param args
	 *            Parametro siempre presente en el metodo main, en este caso no
	 *            se esta usando.
	 */
	public static void main (String[] args) {
		
		Facade f = new OracleConnector();

//		try {
//			q.insertUser(new Usuario(0, "s", "s", 1, "email", "s", new Date(new java.util.Date().getTime())));
//		} catch (EmailAlreadyExistsException e) {
//			System.err.println(e.getMessage());
//		}
		
		//List<Persona> personas = q.getPersonaTrabajo(59);
//		int id = f.getIdObra("Ali G Indahouse", "");
//		System.out.println(id);
//		List<Persona> personas = f.getPersonaTrabajo(f.getIdObra("Ali G Indahouse", ""));
//		for (Persona p : personas) {
//			System.out.println(p.getNombre());
//		}
//		System.out.println(f.getIdPersona("Sacha Baron Cohen"));
		List<Obra> obras = f.getTrabajosPersona(f.getIdPersona("Sacha Baron Cohen"));
		for (Obra p : obras) {
			System.out.println(p.getNombre());
		}
		
		List<Persona> personas = f.getPersonas("Ali G Indahouse");
		for (Persona p : personas) {
			System.out.println(p.getNombre());
		}
		//System.out.println(q.getUser("puta_caliente69@pene.com").getNombre());
		//System.out.println(q.getNumObras());
	}

}
