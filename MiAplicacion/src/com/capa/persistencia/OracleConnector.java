package com.capa.persistencia;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import com.capa.modelo.Obra;
import com.capa.modelo.Persona;
import com.capa.modelo.Usuario;
import com.capa.persistencia.exceptions.EmailAlreadyExistsException;

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
			System.err.println("Error:'" + e.getMessage() + "'");
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
				+ "(nombre, fecha_emision, puntuacion, duracion, nacionalidad, capitulos, ruta_imagen) VALUES"
				+ "('%s', TO_DATE('%s', 'YYYY-MM-DD'),'%d', '%d', '%s', '%d', '%s')",
				nombre, fecha, puntuacion, duracion, nacionalidad, capitulos, ruta_imagen);
		ResultSet rs = null;
		int obra_id = -1;
		
		try {
			rs = executeQuery(sql);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha);
			obra_id = getIdObra(nombre, String.valueOf(calendar.get(Calendar.YEAR)));
			rs.close();
			disconnect();
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
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
			rs.close();
			disconnect();
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
           disconnect();
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
			rs.close();
			disconnect();
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		return count;
	}

	@Override
	public Obra getObra(int ObraId) {
		String sql = String.format("SELECT * FROM obra WHERE id=('%d')", ObraId);
		Obra obra = null;
		ResultSet rs = null;
		
		try {		
			rs = executeQuery(sql);
			if (rs.next()) {
				obra = new Obra(rs.getInt("id"),
						rs.getString("nombre"),
	        			rs.getDate("fecha_emision"), 
	        			rs.getInt("puntuacion"),
	        			rs.getInt("duracion"), 
	        			rs.getInt("capitulos"), 
	        			rs.getString("nacionalidad"),
	        			rs.getString("ruta_imagen"));
			}
			rs.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obra;
	}
	
	public Obra getObra(String name, String year) {
		String sql = String.format("SELECT * FROM obra WHERE nombre=('%s') "
				+ "AND TO_CHAR(fecha_emision, 'YYYY')=('%s')", name, year);
		if (year.length() == 0)
			sql = String.format("SELECT * FROM obra WHERE nombre=('%s')", name);

		Obra obra = null;
		ResultSet rs = null;
		
		try {		
			rs = executeQuery(sql);
			if (rs.next()) {
				obra = new Obra(rs.getInt("id"),
						rs.getString("nombre"),
	        			rs.getDate("fecha_emision"), 
	        			rs.getInt("puntuacion"),
	        			rs.getInt("duracion"), 
	        			rs.getInt("capitulos"), 
	        			rs.getString("nacionalidad"),
	        			rs.getString("ruta_imagen"));
			}
			rs.close();
			disconnect();
		} catch (SQLException e) {
			System.err.println("Error:'" + e.getMessage() + "'");
		}
		
		return obra;
		
	}

	@Override
	public int getIdObra(String obra, String anio) {
		String sql = String.format("SELECT id FROM obra WHERE nombre='%s' "
				+ "AND TO_CHAR(fecha_emision, 'YYYY')=('%s')", obra, anio);
		if (anio.length() == 0)
			sql = String.format("SELECT id FROM obra WHERE nombre='%s'", obra);
	
		ResultSet rs = null;
		int obra_id = -1;
		
		try {	
			rs = executeQuery(sql);
			if (rs.next()) {
				obra_id = rs.getInt(1);
			}
			rs.close();
			disconnect();
		} catch (SQLException e) {
			System.err.println("Error:'" + e.getMessage() + "'");
		}
		return obra_id;
	}

	@Override
	public int insertPersona(String nombre, Date fecha, String sexo, String nacionalidad) {
		String sql = String.format("INSERT INTO Persona"
				+ "(nombre, fecha_nacimiento, sexo, nacionalidad) VALUES"
				+ "('%s', TO_DATE('%s', 'YYYY-MM-DD'),'%s', '%s')",
				nombre, fecha, sexo, nacionalidad);
		ResultSet rs = null;
		int persona_id = -1;
		
		try {
			rs = executeQuery(sql);
			sql = String.format("SELECT id FROM persona WHERE nombre='%s' "
					+ "AND fecha_nacimiento=TO_DATE('%s', 'YYYY-MM-DD')", nombre, fecha);
			rs = executeQuery(sql);
			if (rs.next()) {
				persona_id = rs.getInt(1);
			}
			rs.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persona_id;
	}

	@Override
	public List<Persona> getPersonas(int idObra) {
		return getPersonaTrabajo(idObra);
	}
	
	public List<Persona> getPersonas(String nombreObra) {
		String sql = String.format("SELECT * FROM Persona WHERE id IN "
				+ "(SELECT nombre_persona FROM Trabaja WHERE nombre_obra IN "
				+ "(SELECT id FROM Obra WHERE nombre='%s'))", nombreObra);
		List<Persona> personas = new ArrayList<Persona>();
		ResultSet rs = null;
		
		try {		
			rs = executeQuery(sql);
			while (rs.next()) {
				personas.add(new Persona(rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("sexo"),
						rs.getDate("fecha_nacimiento"),
						rs.getString("nacionalidad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return personas;
	}

	@Override
	public int getIdPersona(String nombre) {
		String sql = String.format("SELECT id FROM persona WHERE nombre='%s'",
				nombre);
	
		ResultSet rs = null;
		int persona_id = -1;
		
		try {	
			rs = executeQuery(sql);
			if (rs.next()) {
				persona_id = rs.getInt(1);
			}
			rs.close();
			disconnect();
		} catch (SQLException e) {
			System.err.println("Error:'" + e.getMessage() + "'");
		}
		return persona_id;
	}

	@Override
	public int insertTrabaja(int idPersona, int idObra, String rol) {
		String sql = String.format("INSERT INTO Trabaja"
				+ "(nombre_persona, nombre_obra, rol) VALUES"
				+ "('%d', '%d', '%s')",
				idPersona, idObra, rol);
		ResultSet rs = null;
		int trabaja_id = -1;
		
		try {
			rs = executeQuery(sql);
			//if (rs.next()) {
				//trabaja_id = rs.getInt(1);
				//System.out.println(obra_id);
			//}
			rs.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trabaja_id;
	}

	@Override
	public List<Obra> getTrabajosPersona(int idPersona) {
		String sql = String.format("SELECT * FROM Obra WHERE id IN "
				+ "(SELECT nombre_obra FROM Trabaja WHERE nombre_persona='%d')", idPersona);
		List<Obra> obras = new ArrayList<Obra>();
		ResultSet rs = null;
		
		try {		
			rs = executeQuery(sql);
			while (rs.next()) {
				obras.add(new Obra(rs.getInt("id"),
						rs.getString("nombre"),
						rs.getDate("fecha_emision"),
						rs.getInt("puntuacion"),
						rs.getInt("duracion"),
						rs.getInt("capitulos"),
						rs.getString("nacionalidad"),
						rs.getString("ruta_imagen")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obras;
	}

	@Override
	public List<Persona> getPersonaTrabajo(int idObra) {
		String sql = String.format("SELECT * FROM Persona WHERE id IN "
				+ "(SELECT nombre_persona FROM Trabaja WHERE nombre_obra='%d')", idObra);
		List<Persona> personas = new ArrayList<Persona>();
		ResultSet rs = null;
		
		try {		
			rs = executeQuery(sql);
			while (rs.next()) {
				personas.add(new Persona(rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("sexo"),
						rs.getDate("fecha_nacimiento"),
						rs.getString("nacionalidad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return personas;
	}

	@Override
	public int insertAccion(String nombre, Date fecha, int idUsuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAccion(Date fecha, int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAccionesUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertPuntuacion(int puntuacion, int idObra, int idAccion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void modifyPuntuacion(int idAccion_Trabaja, int newPuntuacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePuntuacion(int idAccion_Trabaja) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPuntuacion(int idAccion_Trabaja) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> getObraPuntuaciones(int idObra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getUserPuntuaciones(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}