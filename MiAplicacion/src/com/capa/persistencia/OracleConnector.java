package com.capa.persistencia;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import com.capa.modelo.Obra;
import com.capa.modelo.Usuario;

/**
 * Clase para acceder a una BD ORACLE
 */
public class OracleConnector implements Facade {

	/**
	 * Driver para conectar con ORACLE
	 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	/**
	 * Conexion a una BD ORACLE
	 */
	private static final String CONNECTION = "jdbc:oracle:thin:@";

	/**
	 * Host o maquina donde reside la BD a la que se quiere conectar
	 */
	String host = "";
	/**
	 * Puerto del host en el que escucha el servidor de ORACLE, es decir, puerto
	 * al que hay que conectar para acceder a la BD ORACLE
	 */
	String port = "";
	/**
	 * Nombre de la instancia o BD a la que se desea conectar
	 */
	String sid = "";
	/**
	 * CaDena de caracteres con el nombre de usuario, o login, a emplear para
	 * conectarse a la BD
	 */
	String user = "";
	/**
	 * Cadena de caracteres con el password, o contraseña, a emplear para
	 * conectarse a la BD
	 */
	String password = "";
	/**
	 * Conexion con la BD
	 */
	Connection connection = null;

	/**
	 * Metodo constructor. Asigna los valores de usuario, password, host, puerto
	 * y nombre de la bd, para que posteriormente pueda hacerse la conexion
	 */
	public OracleConnector() {
		
		Properties properties = new Properties();

		try {
			properties.load(OracleConnector.class
					.getResourceAsStream("bd.properties"));
			
			this.host = properties.getProperty("database.host");
			this.port = properties.getProperty("database.port");
			this.sid = properties.getProperty("database.sid");
			this.user = properties.getProperty("database.user");
			this.password = properties.getProperty("database.password");

			Class.forName(DRIVER).newInstance();
			
		} catch (InstantiationException e) {
				e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para establecer la conexion JDBC con la BD
	 * <p>
	 * 
	 * @throws SQLException
	 * 
	 * @exception Lanza
	 *                una excepcion en caso de que se produzca algun error
	 */
	private void connect() throws SQLException {
		// Estableciendo la conexion con la BD
		if (port == null) {
			connection = DriverManager.getConnection(CONNECTION + host
					+ ":1521:" + sid, user, password);
		} else {
			connection = DriverManager.getConnection(CONNECTION + host + ":"
					+ port + ":" + sid, user, password);
		}
	}

	/**
	 * Metodo para cerrar la conexion JDBC con la BD
	 */
	private void disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
			connection = null;
		} catch (SQLException sqlE) {
			connection = null;
		}
	}

	private ResultSet executeQuery(String sql) {

		// Creamos una sentencia para poder usarla con la conexion que
		// tenemos abierta
		Statement stmt = null;
		ResultSet rs = null;
		
		// Formulamos la pregunta y obtenemos el resultado
		try {
			connect();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public void insertUser(Usuario user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario loginUser(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertComment(String comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyComment(int id, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> filmComments(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> userComments(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Obra> filmData(int ObraId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numObras() {
		int num_obras = 0;
		String sql = String.format("SELECT COUNT(*) FROM OBRA");
		ResultSet rs = executeQuery(sql);
		
		try {
			if (rs.next()) {
				num_obras = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		return num_obras;
	}

	@Override
	public Usuario getUser(String email) {
		String sql = String.format("SELECT * FROM USUARIO WHERE EMAIL='%s'", email);
		ResultSet rs = executeQuery(sql);
		
		Usuario user = null;
		try {
			if (rs.next()) {
				user = new Usuario(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("sexo"),
						rs.getInt("telefono"),
						rs.getString("email"),
						rs.getString("pass"),
						rs.getDate("nacimiento"));	
			}
			
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
		disconnect();
		
		return user;
	}
}