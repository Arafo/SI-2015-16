package com.capa.persistencia;

import java.util.ArrayList;

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
	
	public int numObras();
	
	public Usuario getUser(String email);
}
