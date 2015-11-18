package com.capa.modelo;

import java.sql.Date;

public class Accion {
	
	private int id;
	private String nombre;
	private Date fecha;
	private int id_usuario;

	public Accion(int id, String nombre, Date fecha, int id_usuario) {
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.id_usuario = id_usuario;
	}
	
	public Accion(String nombre, Date fecha, int id_usuario) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.id_usuario = id_usuario;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
}
