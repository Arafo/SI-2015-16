package com.capa.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class FacadeImpl {

	private Statement conexionMysql;
	private Statement conexionOracle;
	String jose;
	
	public FacadeImpl(){
		//Conexion a mysql
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String mySqlUrl = "jdbc:mysql://hendrix-mysql.cps.unizar.es/miniIMDB";

        Properties userInfo = new Properties();
        userInfo.put("user", "a628726");
        userInfo.put("password", "wasteb81");
        try {
			Connection connection = DriverManager.getConnection(mySqlUrl, userInfo);
			conexionMysql = connection.createStatement();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        System.setProperty("user.timezone","+01:00");
        String myOracleUrl = "jdbc:oracle:thin:@hendrix-oracle.cps.unizar.es:1521:vicious";
        try {
			Connection connection = DriverManager.getConnection(myOracleUrl, userInfo);
			conexionOracle=connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        
	}
	public static void main(String[] args) {
		FacadeImpl e= new FacadeImpl();
	/*	Comentario a= new Comentario("Jose mola", 1);
		a=e.insertComment(a);
		Comentario b = e.getComment(a.getId());
		e.modifyComment(b.getId(), "Jose mola mas");
		Comentario c = e.getComment(a.getId());
		ArrayList<Comentario> comentarios= e.filmComments(1);
		
		System.out.println(a.getId());
		System.out.println(b.getComment());
		System.out.println(c.getComment());
		while(!comentarios.isEmpty()){
			System.out.println(comentarios.remove(0).getComment());
		}
		comentarios=e.userComments("anonimo@anonimo.com");
		while(!comentarios.isEmpty()){
			System.out.println(comentarios.remove(0).getComment());
		}
		*/
		System.out.println(e.numPeliculas());
	}
	
	@Override
	public void insertUser(Usuario user) {
		String comando= String.format("insert into usuario values "
				+ "('%s','%s',TO_DATE('%s', 'DD/MM/YYYY'), "
				+ "'%s', '%s', %s, %s)"
				, user.getNombre(),user.getApellidos(), user.getFecha(),
				user.getDireccion(), user.getEmail(), user.getTelefono(),
				user.getContrase�a());
		try {
			conexionOracle.executeUpdate(comando);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Usuario loginUser(String email, String password) {
		String comando= String.format("select * from usuario where email='%s'",
				email);
		ResultSet resultado=null;
		Usuario returnar=null;
		try {
			resultado=conexionOracle.executeQuery(comando);
			resultado.next();
			if(resultado.getInt("clave")==Integer.parseInt(password)){
				returnar=new Usuario(resultado.getString("nombre"),
						resultado.getString("apellido"),
						resultado.getDate("birthday").getDay(),
						resultado.getDate("birthday").getMonth(),
						resultado.getDate("birthday").getYear(),
						resultado.getString("direccion"),
						resultado.getString("email"),
						Integer.parseInt(resultado.getString("telefono")),
						Integer.parseInt(resultado.getString("clave")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnar;
	}
	@Override
	public Comentario insertComment(Comentario comment) {
		String comando= String.format("insert into comentario values "
				+ "(1,'%d','%s','%s')"
				, comment.getFilmId(), comment.getComment(), comment.getEmail());
		ResultSet resultado=null;
		try {
			conexionOracle.execute("lock table comentario in exclusive mode");			
			conexionOracle.execute(comando);
			conexionOracle.execute("select * from user_sequences");
			resultado=conexionOracle.executeQuery("select contar.currval from dual");
			resultado.next();
			comment.setId(resultado.getInt(1));
			conexionOracle.execute("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}
	@Override
	public void modifyComment(int id, String text) {
		String comando= String.format("update comentario set texto = '%s' "
				+ "where comentarioid=('%s')", text, id);
		try {		
			conexionOracle.execute(comando);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deleteComment(int id) {
		String comando= String.format("delete from comentario where comentarioid="
				+ "('%s')", id);
		try {		
			conexionOracle.execute(comando);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Comentario getComment(int id) {
		String comando= String.format("select * from comentario where comentarioid="
				+ "('%d')", id);
		Comentario returnar=null;
		ResultSet resultado=null;
		try {		
			resultado=conexionOracle.executeQuery(comando);
			resultado.next();
			returnar= new Comentario(resultado.getString("texto"),
					resultado.getString("usuarioid"), 
					Integer.parseInt(resultado.getString("peliculaid")));
			returnar.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnar;
	}
	@Override
	public ArrayList<Comentario> filmComments(int filmId) {
		ArrayList<Comentario> returnar = new ArrayList<>(); 
		String comando= String.format("select * from comentario where PeliculaID="
				+ "('%d')", filmId);
		ResultSet resultado=null;
		try {		
			resultado=conexionOracle.executeQuery(comando);
			resultado.next();
			do{
				Comentario a�adir= new Comentario(resultado.getString("texto"),
					resultado.getString("usuarioid"), 
					Integer.parseInt(resultado.getString("peliculaid")));
				a�adir.setId(Integer.parseInt(resultado.getString("comentarioid")));
				returnar.add(a�adir);
			}while(resultado.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnar;
	}
	@Override
	public ArrayList<Comentario> userComments(String email) {
		ArrayList<Comentario> returnar = new ArrayList<>(); 
		String comando= String.format("select * from comentario where usuarioid="
				+ "('%s')", email);
		ResultSet resultado=null;
		try {		
			resultado=conexionOracle.executeQuery(comando);
			resultado.next();
			do{
				Comentario a�adir= new Comentario(resultado.getString("texto"),
					resultado.getString("usuarioid"), 
					Integer.parseInt(resultado.getString("peliculaid")));
				a�adir.setId(Integer.parseInt(resultado.getString("comentarioid")));
				returnar.add(a�adir);
			}while(resultado.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnar;
	}
	@Override
	public ArrayList<Pelicula> filmData(int filmId) {
		return null;
	}


}
