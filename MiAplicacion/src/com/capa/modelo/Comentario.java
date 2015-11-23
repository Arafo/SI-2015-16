package com.capa.modelo;

import java.sql.Date;

public class Comentario {
	
	private int id;
	private String nombre;
	private String email;
	private String comentario;
	private int puntuacion;
	private Date fecha;
	
	public Comentario(int id_comentario, String nombre, String email, String comentario, int puntuacion, Date fecha) {
		super();
		this.id = id_comentario;
		this.nombre = nombre;
		this.email = email;
		this.comentario = comentario;
		this.puntuacion = puntuacion;
		this.fecha = fecha;
	}
	
	public Comentario(String nombre, String email, String comentario, int puntuacion, Date fecha) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.comentario = comentario;
		this.puntuacion = puntuacion;
		this.fecha = fecha;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id_comentario) {
		this.id = id_comentario;
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
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int rating) {
		this.puntuacion = rating;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}