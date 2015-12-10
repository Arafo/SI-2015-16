package com.capa.modelo.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.FSDirectory;
import com.capa.modelo.Obra;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;

public class SearchLuceneServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String obra = null;
		String query = null;
		String genre = null;
		long startTime = System.nanoTime();
		
		// Recoger el parametro de busqueda
		if (request.getParameter("q") != null) {
			obra = request.getParameter("q");
			query = obra.trim().replaceAll("'", "''");
		}
		
		if (request.getParameter("genre") != null) {
			genre = request.getParameter("genre");
			query = "genero:" + genre;
			obra = query;
		}
		
		List<Obra> obrasList = new ArrayList<Obra>();
		String index = "index";
		String fields [] = {"nombre", "fecha_emision", "genero", "nacionalidad", "plot",
				"awards", "metascore", "imdb_rating", "persona_nombre"};

		// Este path funciona solo con el WAR desplegado
		String contextPath = getServletContext().getRealPath(File.separator);
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(contextPath + index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		searcher.setSimilarity(new BM25Similarity());

		Analyzer analyzer = new SpanishAnalyzer();  
		
		MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);		
		System.out.println("Searching for: " + query.toString());

		TopDocs results = null;
		try {
			results = searcher.search(parser.parse(query), reader.maxDoc());
			ScoreDoc[] hits = results.scoreDocs;
			int numTotalHits = results.totalHits;
			
			int start = 0;
			int end = numTotalHits;
			
			for (int i = start; i < end; i++) {
				Document doc = searcher.doc(hits[i].doc);
				String nombre = doc.get("nombre");
				if (nombre != null) {
					obrasList.add(new Obra(Integer.valueOf((doc.get("id"))),
							doc.get("nombre"),
							new Date(Long.valueOf(doc.get("fecha_emision"))), 
							Integer.valueOf(doc.get("puntuacion")),
							Integer.valueOf(doc.get("duracion")), 
							doc.get("genero"),
							Integer.valueOf(doc.get("capitulos")), 
							doc.get("nacionalidad"),
							doc.get("ruta_imagen"),
							doc.get("plot"),
							doc.get("awards"),
							Integer.valueOf(doc.get("metascore")),
							Double.valueOf(doc.get("imdb_rating")),
							Integer.valueOf(doc.get("imdb_votes")))); 	
				}
				// explain the scoring function
				//System.out.println(searcher.explain(query, hits[i].doc));
			}
			
			Facade f = new OracleConnector();
			List<Obra> mejor_puntuadas = f.getMejorPuntuadas(5);
			List<Obra> mas_comentadas = f.getMasComentadas(5);
			
			// Extraer de la BD las entradas que cumplan la busqueda
			float endTime = (System.nanoTime() - startTime)/1e9f;
			request.setAttribute("obrasList", obrasList);
			request.setAttribute("obrasListSize", obrasList.size());
			request.setAttribute("time", endTime);
			request.setAttribute("mejor_puntuadas", mejor_puntuadas);
			request.setAttribute("mas_comentadas", mas_comentadas);
			request.setAttribute("query", obra);
			
			// Mostrar los resultados de la busqueda
			RequestDispatcher view = request.getRequestDispatcher("search.jsp");
			view.forward(request, response);
		} catch (Exception e) {
			Facade f = new OracleConnector();
			List<Obra> mejor_puntuadas = f.getMejorPuntuadas(5);
			List<Obra> mas_comentadas = f.getMasComentadas(5);
			
			float endTime = (System.nanoTime() - startTime)/1e9f;
			request.setAttribute("obrasList", obrasList);
			request.setAttribute("obrasListSize", obrasList.size());
			request.setAttribute("time", endTime);
			request.setAttribute("mejor_puntuadas", mejor_puntuadas);
			request.setAttribute("mas_comentadas", mas_comentadas);
			request.setAttribute("query", obra);
			
			
			// Mostrar los resultados de la busqueda
			RequestDispatcher view = request.getRequestDispatcher("search.jsp");
			view.forward(request, response);
		}
		
	}
}