package com.capa.modelo;

import java.util.Date;

public class Obra {
	
	private int id;
	private String nombre;
	private Date fecha_emision;
	private int puntuacion;
	private int duracion;
	private int capitulos;
	private String nacionalidad;
	private String ruta_imagen;
	
	public Obra(int id, String nombre, Date fecha_emision, int puntuacion, int duracion, int capitulos,
			String nacionalidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_emision = fecha_emision;
		this.puntuacion = puntuacion;
		this.duracion = duracion;
		this.capitulos = capitulos;
		this.nacionalidad = nacionalidad;
	}
	
	public Obra(int id, String nombre, Date fecha_emision, int puntuacion, int duracion, int capitulos,
			String nacionalidad, String ruta_imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_emision = fecha_emision;
		this.puntuacion = puntuacion;
		this.duracion = duracion;
		this.capitulos = capitulos;
		this.nacionalidad = nacionalidad;
		this.ruta_imagen = ruta_imagen;
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
	public Date getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(int capitulos) {
		this.capitulos = capitulos;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}	
	public String getRuta_imagen() {
		return ruta_imagen;
	}
	public void setRuta_imagen(String ruta_imagen) {
		this.ruta_imagen = ruta_imagen;
	}
}
