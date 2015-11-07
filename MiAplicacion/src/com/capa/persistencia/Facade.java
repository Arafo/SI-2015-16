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
	// TABLA OBRA (ACABADA)
	/////////////////////////////////////////////////////////////
		
	// Inserta  la Obra cuyo nombres es "nombre", fecha de emision es "fecha",
	// puntuacion es "puntuacion", duracion es "duracion", nacionalidad es "nacionalidad"
	// capitulos es "capitulos" y ruta de la imagen es "ruta_imagen"
	// Devuelve un identificador id correspondiente a la Obra insertada
	public int insertObra(String nombre, Date fecha, int puntuacion, int duracion, 
			String nacionalidad, int capitulos, String ruta_imagen);
	// Devuelve la Obra cuyo cuyo id es igual a "ObraId"
	public Obra getObra(int ObraId);
	// Devuelve la Obra cuyo cuyo nombre es "nombre" y fecha de emision es "year"
	public Obra getObra(String nombre, String year);
	// Devuelve la id de la obra cuyo nombre es "nombre" y fecha de emision es "anio"
	public int getIdObra(String nombre, String anio);
	// Devuelve una lista de Obra que contiene "nombre" en el campo nombre
	public List<Obra> getObras(String nombre);
	// Devuelve una lista de Obras con tamaño igual a noOfRecords a partir de offset
	public List<Obra> getObras(int offset, int noOfRecords);
	// Devuelve el numero de Obras existentes
	public int getNumObras();
	
	/////////////////////////////////////////////////////////////
	// TABLA PERSONA (NO ACABADA)
	/////////////////////////////////////////////////////////////
	
	public int insertPersona(String nombre, Date fecha, String sexo, String nacionalidad);
	public List<Persona> getPersonas(int idObra);
	public List<Persona> getPersonas(String nombre);
	public int getIdPersona(String nombre);
	
	/////////////////////////////////////////////////////////////
	// TABLA TRABAJA (NO ACABADA)
	/////////////////////////////////////////////////////////////
	
	public int insertTrabaja(int idPersona, int idObra, String rol);
	public List<Obra> getTrabajosPersona(int idPersona);
	public List<Persona> getPersonaTrabajo(int idObra);
	
	/////////////////////////////////////////////////////////////
	// TABLA USUARIO (NO ACABADA)
	/////////////////////////////////////////////////////////////
	
	public void insertUser(Usuario user) throws EmailAlreadyExistsException;
	public Usuario loginUser(String email, String password);
	public Usuario getUser(String email);
	
	/////////////////////////////////////////////////////////////
	// TABLA ACCION (NO ACABADA)
	/////////////////////////////////////////////////////////////
	
	public int insertAccion(String nombre, Date fecha, int idUsuario);
	public String getAccion(Date fecha, int idUsuario);
	public List<String> getAccionesUsuario(int idUsuario);
	
	/////////////////////////////////////////////////////////////
	// TABLA ACCION_TRABAJA (NO ACABADA)
	/////////////////////////////////////////////////////////////
	
	public int insertComment(String comment, int id_obra, int id_accion);
	public void modifyComment(int id, String text);
	public void deleteComment(int id);
	// Devuelve el String con id = [id]
	public String getComment(int id);
	//Devuelve una lista de Strings de una pelicula
	public ArrayList<String> ObraComments(int filmId);
	// Devuelve una lista de Strings de un usuario
	public ArrayList<String> userComments(String email);
	
	public int insertPuntuacion(int puntuacion, int idObra, int idAccion);
	public void modifyPuntuacion(int idAccion_Trabaja, int newPuntuacion);
	public void deletePuntuacion(int idAccion_Trabaja);
	public int getPuntuacion(int idAccion_Trabaja);
	public List<Integer> getObraPuntuaciones(int idObra);
	public List<Integer> getUserPuntuaciones(String email);

}
