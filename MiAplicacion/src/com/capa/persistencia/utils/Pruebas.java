package com.capa.persistencia.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.capa.modelo.Accion;
import com.capa.modelo.Comentario;
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
	public static void main(String[] args) {

		Facade f = new OracleConnector();

		/**
		 * Obtener personas para una obra dado su id
		 */
		// List<Persona> personas = q.getPersonaTrabajo(59);
		// List<Persona> personas = f.getPersonaTrabajo(f.getIdObra("Ali G Indahouse", ""));
		// for (Persona p : personas) {
		// System.out.println(p.getNombre());
		// }
		
		/**
		 * Obtener la id de una obra dado su nombre 
		 */
		// int id = f.getIdObra("Ali G Indahouse", "");
		// System.out.println(id);
		
		/**
		 * Obtener la id de una persona dado su nombre
		 */
		// System.out.println(f.getIdPersona("Sacha Baron Cohen"));
		
		/**
		 * Obtener las obras en las que ha trabajado una persona dado su nombre
		 */
//		List<Obra> obras = f.getTrabajosPersona(f.getIdPersona("Sacha Baron Cohen"));
//		for (Obra p : obras) {
//			System.out.println(p.getNombre());
//		}

		/**
		 * Obtener las personas que han trabajado en una obra dado su nombre
		 */
//		List<Persona> personas = f.getPersonas("Ali G Indahouse");
//		for (Persona p : personas) {
//			System.out.println(p.getNombre());
//		}

		/**
		 * Insertar un usuario
		 */
		int i=0;
		try {
		while(true){
			try {
				int exito = f.insertUser(new Usuario("Rafa", "M", 976111111, "rafa_marcen"+i+"@hotmail.com", encodeMd5("pass"),
						new Date(new java.util.Date().getTime()), "4", "Calle Tomate"));
				System.out.println(exito);
			} catch (EmailAlreadyExistsException e) {
				System.err.println(e.getMessage());
			}
			i++;
		}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(i);
		}
		/**
		 * Insertar una accion
		 */
//		int accion = f.insertAccion("comentario", new Date(new java.util.Date().getTime()), f.getUser("Fallout1").getId());
//		int accion_obra = f.insertComment("Esta pelicula iba de monos locos?", 0, f.getIdObra("Ali G Indahouse", "2002"), accion);
//		System.out.println(accion);
//		System.out.println(accion_obra);
		
		/**
		 * Obtener los comentarios de una obra
		 */
//		List<Comentario> comentarios = f.ObraComments(f.getIdObra("Ali G Indahouse", "2002"));
//		for (Comentario comment : comentarios)
//			System.out.println(comment.getComentario() + ", " + comment.getNombre() + ", " + comment.getFecha());
//		
//		/**
//		 * Modificar un comentario
//		 */
//		f.modifyComment(comentarios.get(0).getId_comentario(), "Otra modificacion del comentario", new Date(new java.util.Date().getTime()));
//		comentarios = f.ObraComments(f.getIdObra("Ali G Indahouse", "2002"));
//		System.out.println();
//		for (Comentario comment : comentarios)
//			System.out.println(comment.getId_comentario() + ", " + comment.getComentario() + ", " + comment.getNombre() + ", " + comment.getFecha());
//		
//		/**
//		 * Borrar comentarios
//		 */
//		f.deleteComment(comentarios.get(0).getId_comentario());
//		comentarios = f.ObraComments(f.getIdObra("Ali G Indahouse", "2002"));
//		System.out.println();
//		for (Comentario comment : comentarios)
//			System.out.println(comment.getId_comentario() + ", " + comment.getComentario() + ", " + comment.getNombre() + ", " + comment.getFecha());
		
		// System.out.println(q.getUser("puta_caliente69@pene.com").getNombre());
		// System.out.println(q.getNumObras());
		
		/**
		 * Obtener accion de un usuario
		 */
//		List<Accion> acciones = f.getAccionesUsuario(5);
//		for (Accion a: acciones)
//			System.out.println(a.getNombre() + ", " + a.getFecha());
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
		} catch (NoSuchAlgorithmException e) {
			
		}
		return md5;
	}

}
