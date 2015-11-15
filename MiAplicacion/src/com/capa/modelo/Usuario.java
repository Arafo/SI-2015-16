package com.capa.modelo;

import java.sql.Date;

public class Usuario {
	
	private int id;
	private String nombre;
	private String sexo;
	private int telefono;
	private String email;
	private String pass;
	private Date nacimiento;
	
	public Usuario(int id, String nombre, String sexo, int telefono, String email, String pass, Date nacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sexo = sexo;
		this.telefono = telefono;
		this.email = email;
		this.pass = pass;
		this.nacimiento = nacimiento;
	}
	
	public Usuario(String nombre, String sexo, int telefono, String email, String pass, Date nacimiento,
			String surname, String address) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
		this.telefono = telefono;
		this.email = email;
		this.pass = pass;
		this.nacimiento = nacimiento;
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
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}


}
