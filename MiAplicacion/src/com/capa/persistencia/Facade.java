package com.capa.persistencia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.capa.modelo.Obra;
import com.capa.modelo.Usuario;

public interface Facade {

	public void insertUser(Usuario user) throws EmailAlreadyExistsException;
	public Usuario loginUser(String email, String password);
	
	public int insertComment(String comment, int id_obra, int id_accion);
	public void modifyComment(int id, String text);
	public void deleteComment(int id);

	
	/**
	 * @return devuelve el String con id = [id]
	 */
	public String getComment(int id);
	
	/**
	 * @return devuelve una lista de Strings de una pelicula
	 */
	public ArrayList<String> ObraComments(int filmId);
	
	/**
	 * @return devuelve una lista de Strings de un usuario
	 */
	public ArrayList<String> userComments(String email);
	
	/**
	 * @return devuelve los datos de una pelicula en concreto
	 */
	public ArrayList<Obra> ObraData(int ObraId);
	
	public int insertObra(String nombre, Date fecha, int puntuacion, int duracion, 
			String nacionalidad, int capitulos, String ruta_imagen);
	
	/**
	 * @param nombre
	 * @return Devuelve los datos de las obras que contienen nombre
	 */
	public List<Obra> getObras(String nombre);
	
	/**
	 * @param offset
	 * @param noOfRecords
	 * @return Devuelve una lista de obras con tamaño igual a noOfRecords
	 * 		   a partir de offset
	 */
	public List<Obra> getObras(int offset, int noOfRecords);
	
	public int getNumObras();
		
	public Usuario getUser(String email);
	
	
}
