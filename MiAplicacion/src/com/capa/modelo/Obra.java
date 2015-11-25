package com.capa.modelo;

import java.util.Date;

public class Obra {
	
	private int id;
	private String nombre;
	private Date fecha_emision;
	private int puntuacion;
	private int duracion;
	private String genero;
	private int capitulos;
	private String nacionalidad;
	private String ruta_imagen;
	private String plot;
	private String awards;
	private int metascore;
	private double imdb_rating;
	private int imdb_votes;
	private String num_comentarios;
	private String avg_puntuacion;
	
	public Obra(int id, String nombre, Date fecha_emision, int puntuacion, int duracion, String genero, int capitulos,
			String nacionalidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_emision = fecha_emision;
		this.puntuacion = puntuacion;
		this.duracion = duracion;
		this.genero = genero;
		this.capitulos = capitulos;
		this.nacionalidad = nacionalidad;
		this.num_comentarios = null;
	}
	
	public Obra(int id, String nombre, Date fecha_emision, int puntuacion, int duracion, String genero, int capitulos,
			String nacionalidad, String ruta_imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_emision = fecha_emision;
		this.puntuacion = puntuacion;
		this.duracion = duracion;
		this.genero = genero;
		this.capitulos = capitulos;
		this.nacionalidad = nacionalidad;
		this.ruta_imagen = ruta_imagen;
		this.num_comentarios = null;
	}
	
	public Obra(int id, String nombre, Date fecha_emision, int puntuacion, int duracion, String genero, int capitulos,
			String nacionalidad, String ruta_imagen, String plot, String awards, int metascore,
			double imdb_rating, int imdb_votes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_emision = fecha_emision;
		this.puntuacion = puntuacion;
		this.duracion = duracion;
		this.genero = genero;
		this.capitulos = capitulos;
		this.nacionalidad = nacionalidad;
		this.ruta_imagen = ruta_imagen;
		this.plot = plot;
		this.awards = awards;
		this.metascore = metascore;
		this.imdb_rating = imdb_rating;
		this.imdb_votes = imdb_votes;
	}
	
	public Obra(int id, String nombre, Date fecha_emision, int puntuacion, int duracion, String genero, int capitulos,
			String nacionalidad, String ruta_imagen, String plot, String awards, int metascore,
			double imdb_rating, int imdb_votes, String num_comentarios, String avg_puntuacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_emision = fecha_emision;
		this.puntuacion = puntuacion;
		this.duracion = duracion;
		this.genero = genero;
		this.capitulos = capitulos;
		this.nacionalidad = nacionalidad;
		this.ruta_imagen = ruta_imagen;
		this.plot = plot;
		this.awards = awards;
		this.metascore = metascore;
		this.imdb_rating = imdb_rating;
		this.imdb_votes = imdb_votes;
		this.num_comentarios = num_comentarios != null ? num_comentarios : "0";
		this.avg_puntuacion = avg_puntuacion != null ? avg_puntuacion : "0";
	}

	public Obra(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Obra(int id, String nombre, String num_comentarios, String avg_puntuacion) {
		this.id = id;
		this.nombre = nombre;
		this.num_comentarios = num_comentarios != null ? num_comentarios : "0";
		this.avg_puntuacion = avg_puntuacion != null ? avg_puntuacion : "0";
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
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public int getMetascore() {
		return metascore;
	}

	public void setMetascore(int metascore) {
		this.metascore = metascore;
	}

	public double getImdb_rating() {
		return imdb_rating;
	}

	public void setImdb_rating(double imdb_rating) {
		this.imdb_rating = imdb_rating;
	}

	public int getImdb_votes() {
		return imdb_votes;
	}

	public void setImdb_votes(int imdb_votes) {
		this.imdb_votes = imdb_votes;
	}

	public String getNum_comentarios() {
		return num_comentarios;
	}

	public void setNum_comentarios(String num_comentarios) {
		this.num_comentarios = num_comentarios;
	}
	
	public String getAvg_puntuacion() {
		return avg_puntuacion;
	}
	
	public void setAvg_puntuacion(String avg_puntuacion) {
		this.avg_puntuacion = avg_puntuacion;
	}
}