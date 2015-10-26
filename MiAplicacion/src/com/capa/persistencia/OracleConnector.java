package com.capa.persistencia;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
	public void insertUser(Usuario user) throws EmailAlreadyExistsException {
		String sql = String.format("SELECT * FROM USUARIO WHERE EMAIL='%s'",
				user.getEmail());
		ResultSet rs = executeQuery(sql);
		try {
			if (!rs.next()) {
				sql = String.format("INSERT INTO USUARIO"
						+ "(id, nombre, sexo, telefono, email, pass, nacimiento) "
						+ "VALUES (user_seq.NEXTVAL, %d, '%s', '%s', %d, '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'))",
						user.getNombre(), user.getSexo(), user.getTelefono(),
						user.getEmail(), user.getPass(), user.getNacimiento());				
			} else {
				throw new EmailAlreadyExistsException("Error al insertar usuario,"
						+ " email '" + user.getEmail() + 
						"' ya existe en la base de datos.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		executeQuery(sql);
	}

	@Override
	public Usuario loginUser(String email, String password) {
		String sql = String.format("SELECT * FROM USUARIO WHERE EMAIL='%s'", email);
		ResultSet rs = null;
		Usuario user = null;
		
		try {
			rs = executeQuery(sql);
			if (rs.next() && rs.getString("pass").equals(password)) {
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
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public int insertComment(String comment, int id_obra, int id_accion) {
		String sql = String.format("INSERT INTO accion_obra"
				+ "(id, id_obra, id_accion, puntuacion, ) VALUES"
				+ "(0,'%d','%s','%s')", id_obra, id_accion, comment);
		ResultSet rs = null;
		int comment_id = -1;
		
		try {
			rs = executeQuery(sql);
			if (rs.next()) {
				comment_id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment_id;
	}

	@Override
	public void modifyComment(int id, String new_comment) {
		String sql = String.format("UPDATE accion_obra SET comentario = '%s' "
				+ "WHERE id=%d", new_comment, id);
		executeQuery(sql);		
	}

	@Override
	public void deleteComment(int id) {
		String sql = String.format("DELETE FROM accion_obra WHERE id=%d", id);
		executeQuery(sql);
	}

	@Override
	public String getComment(int id) {
		String sql = String.format("SELECT * FROM accion_obra WHERE id=('%d')", id);
		String comment = null;
		ResultSet rs = null;
		
		try {		
			rs = executeQuery(sql);
			if (rs.next()) {
				comment = rs.getString("comentario");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return comment;
	}

	@Override
	public ArrayList<String> ObraComments(int ObraId) {
		ArrayList<String> comments = new ArrayList<>(); 
		String sql = String.format("SELECT * FROM accion_obra WHERE id_obra="
				+ "('%d')", ObraId);
		ResultSet rs = null;
		
		try {		
			rs = executeQuery(sql);
			while (rs.next()) {
				comments.add(rs.getString("comentario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return comments;
	}

	@Override
	public ArrayList<String> userComments(String email) {
		ArrayList<String> comments = new ArrayList<>(); 
		String sql= String.format("SELECT * FROM accion_obra WHERE id="
				+ "('%s')", email);
		ResultSet rs = null;
		
		try {		
			rs = executeQuery(sql);
			while (rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

	@Override
	public ArrayList<Obra> ObraData(int ObraId) {
		return null;
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
		
	public int insertObra(String nombre, Date fecha, int puntuacion, int duracion, 
			String nacionalidad, int capitulos, String ruta_imagen) {
		String sql = String.format("INSERT INTO Obra"
				+ "(id, nombre, fecha_emision, puntuacion, duracion, nacionalidad, capitulos, ruta_imagen) VALUES"
				+ "(obra_seq.NEXTVAL, '%s', TO_DATE('%s', 'YYYY-MM-DD'),'%d', '%d', '%s', '%d', '%s')",
				nombre, fecha, puntuacion, duracion, nacionalidad, capitulos, ruta_imagen);
		ResultSet rs = null;
		int obra_id = -1;
		
		try {
			rs = executeQuery(sql);
			if (rs.next()) {
				obra_id = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obra_id;
	}
	
	public List<Obra> getObras(String nombre)  {
		String sql = String.format("SELECT * FROM obra", 
				nombre.toUpperCase());
		List<Obra> data = new ArrayList<Obra>();

		ResultSet rs = executeQuery(sql);
		
		try {
			while (rs.next()) {
				if (rs.getString("nombre").toLowerCase().contains(nombre.toLowerCase())) {
		        	  data.add(new Obra(rs.getInt("id"),
		        			  rs.getString("nombre"),
		        			  rs.getDate("fecha_emision"), 
		        			  rs.getInt("puntuacion"),
		        			  rs.getInt("duracion"), 
		        			  rs.getInt("capitulos"), 
		        			  rs.getString("nacionalidad"),
		        			  rs.getString("ruta_imagen"))); 
				}
 
			}
		} catch (Exception e) {
			e.printStackTrace();	
		} 
		
		return data;	
	}

	public List<Obra> getObras(int offset, int noOfRecords) {
		
		String sql = "SELECT * FROM ("
				+ "SELECT rownum rnum, a.* "
				+ "FROM("
				+ "SELECT * "
				+ "FROM obra "
				+ "ORDER BY nombre "
				+ ") a "
				+ "WHERE rownum <=" + (offset + noOfRecords)
				+ ")"
				+ "WHERE rnum >" + offset;
       List<Obra> list = new ArrayList<Obra>();
       
       try {
           ResultSet rs = executeQuery(sql);
           while (rs.next()) {
        	   list.add(new Obra(rs.getInt("id"),
        			   rs.getString("nombre"),
        			   rs.getDate("fecha_emision"), 
        			   rs.getInt("puntuacion"),
        			   rs.getInt("duracion"), 
        			   rs.getInt("capitulos"), 
        			   rs.getString("nacionalidad"),
        			   rs.getString("ruta_imagen")));
           }
           rs.close();
           
       } catch (SQLException e) {
           e.printStackTrace();
       }
       
       return list;
	}

	public int getNumObras() {
		String sql = "select count(id) AS rowcount from obra";
		ResultSet rs = executeQuery(sql);
		int count = 0;
		
		try {
			if (rs.next()) {
				count = rs.getInt("rowcount");
				rs.close();
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		return count;
	}
}