package com.capa.modelo;

import java.sql.Date;

public class Comentario {
	
	private int id_comentario;
	private String nombre;
	private String comentario;
	private Date fecha;
	
	public Comentario(int id_comentario, String nombre, String comentario, Date fecha) {
		super();
		this.id_comentario = id_comentario;
		this.nombre = nombre;
		this.comentario = comentario;
		this.fecha = fecha;
	}
	
	public Comentario(String nombre, String comentario, Date fecha) {
		super();
		this.nombre = nombre;
		this.comentario = comentario;
		this.fecha = fecha;
	}
	
	
	public int getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(int id_comentario) {
		this.id_comentario = id_comentario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}