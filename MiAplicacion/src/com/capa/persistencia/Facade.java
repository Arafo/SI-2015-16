package com.capa.persistencia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.capa.modelo.Obra;
import com.capa.modelo.Persona;
import com.capa.modelo.Usuario;
import com.capa.persistencia.exceptions.EmailAlreadyExistsException;

public interface Facade {


	/////////////////////////////////////////////////////////////
	// TABLA OBRA
	/////////////////////////////////////////////////////////////
		
	public int insertObra(String nombre, Date fecha, int puntuacion, int duracion, 
			String nacionalidad, int capitulos, String ruta_imagen);
	
	/**
	 * @return devuelve los datos de una pelicula en concreto
	 */
	public Obra getObra(int ObraId);
	public Obra getObra(String name, String year);
	
	public int getIdObra(String obra, String anio);
	
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
	
	/////////////////////////////////////////////////////////////
	// TABLA PERSONA
	/////////////////////////////////////////////////////////////
	
	public int insertPersona(String nombre, Date fecha, String sexo, String nacionalidad);
	public List<Persona> getPersonas(int idObra);
	public int getIdPersona(String nombre);
	
	/////////////////////////////////////////////////////////////
	// TABLA TRABAJA
	/////////////////////////////////////////////////////////////
	
	public int insertTrabaja(int idPersona, int idObra, String rol);
	public List<Obra> getTrabajosPersona(int idObra);
	public List<Persona> getPersonaTrabajo(int idPersona);
	
	/////////////////////////////////////////////////////////////
	// TABLA USUARIO
	/////////////////////////////////////////////////////////////
	
	public void insertUser(Usuario user) throws EmailAlreadyExistsException;
	public Usuario loginUser(String email, String password);
	public Usuario getUser(String email);
	
	/////////////////////////////////////////////////////////////
	// TABLA ACCION
	/////////////////////////////////////////////////////////////
	
	public int insertAccion(String nombre, Date fecha, int idUsuario);
	public String getAccion(Date fecha, int idUsuario);
	public List<String> getAccionesUsuario(int idUsuario);
	
	/////////////////////////////////////////////////////////////
	// TABLA ACCION_TRABAJA
	/////////////////////////////////////////////////////////////
	
	public int insertComment(String comment, int id_obra, int id_accion);
	public void modifyComment(int id, String text);
	public void deleteComment(int id);
	
	public int insertPuntuacion(int puntuacion, int idObra, int idAccion);
	public void modifyPuntuacion(int idAccion_Trabaja, int newPuntuacion);
	public void deletePuntuacion(int idAccion_Trabaja);
	
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
	
	public int getPuntuacion(int idAccion_Trabaja);
	public List<Integer> getObraPuntuaciones(int idObra);
	public List<Integer> getUserPuntuaciones(String email);
}
