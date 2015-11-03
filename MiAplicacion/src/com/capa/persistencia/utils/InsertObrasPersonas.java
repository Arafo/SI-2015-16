package com.capa.persistencia.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class InsertObrasPersonas {
	
	final static String FILE_PATH = "utils/carga.txt";

	public static void main(String[] args) {
		boolean update = false;
		Scanner file = null;
		Facade f = new OracleConnector();
		try {
			file = new Scanner(new File(FILE_PATH));
			while (file.hasNextLine()) {
				String pelicula[] = file.next().split(";");
				if (update || f.getObra(pelicula[0].replaceAll("\\+", " ").replaceAll("'", "''"), pelicula[1]) == null)
					insertObra(pelicula[0], pelicula[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void insertObra(String name, String year) {
		String url = String.format("http://www.omdbapi.com/?t=%s&y=%s&r=json", 
				name, year);
		
		try {
			String json = readUrl(url);
			Gson gson = new Gson();
			Page page = gson.fromJson(json, Page.class);
			
			if (!page.Response.equals("False") && !page.Poster.equals("N/A")) {
				String imagePath = "images/" + 
						page.Title.replaceAll("[\\s\\:\\'\\,\\.\\·]", "") + "_" + 
						page.Year + ".jpg";
				saveImagefromUrl(page.Poster, "WebContent/" + imagePath);
				resizeImage(214, 320, "WebContent/" + imagePath);
				
				// Fecha de estreno de la obra
				DateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
				Date date = new Date(0);
				if (page.Released != null && !page.Released.equals("N/A")) {
					date = format.parse(page.Released);
				}

//				OracleConnector oc = new OracleConnector();
//				oc.insertObra(page.Title.replaceAll("'", "''"), 
//						new java.sql.Date(date.getTime()), 
//						4, 
//						Integer.valueOf(page.Runtime.split(" ")[0]),
//						page.Country, 1, imagePath);
				insertActors(name, year);
				
			} 
			else {
				System.err.println("La pelicula " + name + " no existe");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void insertActors(String name, String year) {
		String url = String.format("http://www.myapifilms.com/imdb?"
				+ "title=%s&format=JSON&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=N&exactFilter=0&limit=1&"
				+ "year=%s&forceYear=0&lang=en-us&actors=S&biography=1&trailer=0&uniqueName=0&filmography=0&bornDied=1&starSign=0&"
				+ "actorActress=1&actorTrivia=0&movieTrivia=0&awards=0&moviePhotos=N&movieVideos=N&similarMovies=0&adultSearch=0",
				name, year);

		try {
			String json = readUrl(url);
			ListActors[] actorsWrapper = new Gson().fromJson(json, ListActors[].class);
			List<Actor> actors = actorsWrapper[0].actors;
			System.out.println(name.toUpperCase());
			for (Actor i : actors) {
				// Fecha de nacimiento
				String date = "";
				if (i.biography.dateOfBirth != null) {
					date = i.biography.dateOfBirth.substring(i.biography.dateOfBirth.length() - 4);
				}
				// Pais de origen
				String country = "";
				if (i.biography.placeOfBirth != null) {
					String[] place = i.biography.placeOfBirth.split(",");
					country = place[place.length - 1].trim();
				}
				// Sexo
				String sex = "U";
				if (i.biography.actorActress != null) {
					sex = i.biography.actorActress.equals("Actor") ? "H" : "M";
				}
				
				System.out.println(i.actorName + ", " + date + ", " + sex + ", " + country);
				// Insertar actor
				//Facade f = new OracleConnector();
				//f.insertActor();
				
				insertRelacionObraActor();
			}
		} catch (IOException e) {
			System.err.println("Error al descargar el archivo JSON de la obra " + name);
		} catch (JsonSyntaxException e1) {
			System.err.println("Error al encontrar los actores de la obra " + name);
		}
	}
	
	private static void insertRelacionObraActor() {
		
	}

	/**
	 * Fuente http://stackoverflow.com/questions/7467568/parsing-json-from-url (30-10-2015)
	 * @param urlString
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	private static String readUrl(String urlString) throws IOException {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[2048];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	/**
	 * Fuente: http://www.avajava.com/tutorials/lessons/how-do-i-save-an-image-from-a-url-to-a-file.html (30-10-2015)
	 * @param urlString
	 * @param savePath
	 * @throws Exception
	 */
	private static void saveImagefromUrl(String urlString, String savePath) throws Exception {
		URL url = new URL(urlString);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(savePath);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

	/**
	 * Fuente : https://github.com/valarion/Videjuegos1415G5/blob/master/src/videjouegos1415g5/gfx/ScaleImg.java (1-10-2015)
	 * @param width
	 * @param height
	 * @param imagePath
	 * @throws IOException
	 */
	private static void resizeImage(int width, int height, String imagePath) throws IOException {
		BufferedImage img = ImageIO.read(new File(imagePath));		
	    BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = resized.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    		RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	    g.drawImage(img, 0, 0, width, height, null);
	    g.dispose();
		
		ImageIO.write(resized, "jpg", new File(imagePath));
	}

	static class Page {
		String Title;
		String Year;
		String Released;
		String Runtime;
		String Genre;
		String Director;
		String Writer;
		String Actors;
		String Plot;
		String Awards;
		String Poster;
		String Metascore;
		String imdbRating;
		String imdbVotes;
		String Response;
	}
	
	static class Item {
		String name;
		String year;
		public Item(String name, String year) {
			this.name = name;
			this.year = year;
		}
	}
	
	static class ListActors {
		public List<Actor> actors;
	}
	
	static class Actor {
		String actorName;
		Biography biography;
	}
	
	static class Biography {
		String dateOfBirth;
		String actorActress;
		String placeOfBirth;
	}
}