package com.capa.modelo;

import java.util.Date;

public class Persona {
	
	private int id;
	private String nombre;
	private String sexo;
	private Date nacimiento;
	private String nacionalidad;
	private String rol;
	
	public Persona(int id, String nombre, String sexo, Date nacimiento, String nacionalidad, String rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sexo = sexo;
		this.nacimiento = nacimiento;
		this.nacionalidad = nacionalidad;
		this.rol = rol;
	}
	
	public Persona(String nombre, String sexo, Date nacimiento, String nacionalidad, String rol) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
		this.nacimiento = nacimiento;
		this.nacionalidad = nacionalidad;
		this.rol = rol;
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}
