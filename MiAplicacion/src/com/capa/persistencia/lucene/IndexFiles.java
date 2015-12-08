package com.capa.persistencia.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.capa.modelo.Obra;
import com.capa.modelo.Persona;
import com.capa.persistencia.Facade;
import com.capa.persistencia.OracleConnector;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 * Aplicación de linea de comando para indexar todos los ficheros de texto en un
 * directorio. 
 * Uso: java IndexFiles -index indexPath -docs docsPath [-update]
 */
public class IndexFiles {

	/** Indexa todos los ficheros de texto en un directorio.
	 * @param args Argumentos de linea de comandos
	 */
	public static void main(String[] args) {
		String usage = "java IndexFiles -index <indexPath> [-update]";
		String indexPath = "index";
		boolean create = true;
		for (int i = 0; i < args.length; i++) {
			if ("-index".equals(args[i])) {
				indexPath = args[i + 1];
				i++;
			} else if ("-update".equals(args[i])) {
				create = false;
			}
		}

		if (indexPath == null) {
			System.err.println("Usage: " + usage);
			System.exit(1);
		}
		
		// Conexion con la base de datos
		Facade facade = new OracleConnector();

		Date start = new Date();
		try {
			System.out.println("Indexing to directory '" + indexPath + "'...");

			Directory dir = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new SpanishAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

			if (create) {
				// Crea un nuevo indice en el directorio, eliminando
				// cualquier documento que haya sido indexado previamente.
				iwc.setOpenMode(OpenMode.CREATE);
			} else {
				// Añade nuevos documentos a un indice existente.
				iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			}

			IndexWriter writer = new IndexWriter(dir, iwc);
			indexDocs(writer, facade);

			writer.close();

			Date end = new Date();
			System.out.println(end.getTime() - start.getTime() + " total milliseconds");

		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param writer
	 * @param db
	 * @throws IOException
	 */
	private static void indexDocs(IndexWriter writer, Facade db) throws IOException {
		// Obtener los datos
		List<Obra> obras = db.getAllObras();
		List<Persona> personas = db.getAllPersonas();
		
		for (Obra obra : obras) {
			// Crea un nuevo documento vacio
			Document doc = new Document();
			
			// Campos de la obra a indexar
			doc.add(new StringField("id", String.valueOf(obra.getId()), Field.Store.YES));
			doc.add(new TextField("nombre", obra.getNombre(), Field.Store.YES));
			doc.add(new LongField("fecha_emision", obra.getFecha_emision().getTime(), Field.Store.YES));
			doc.add(new IntField("puntuacion", obra.getPuntuacion(), Field.Store.YES));
			doc.add(new IntField("duracion", obra.getDuracion(), Field.Store.YES));
			doc.add(new TextField("genero", obra.getGenero(), Field.Store.YES));
			doc.add(new IntField("capitulos", obra.getCapitulos(), Field.Store.YES));
			doc.add(new TextField("nacionalidad", obra.getNacionalidad(), Field.Store.YES));
			doc.add(new StringField("ruta_imagen", obra.getRuta_imagen(), Field.Store.YES));
			doc.add(new TextField("plot", obra.getPlot(), Field.Store.YES));
			doc.add(new TextField("awards", obra.getAwards(), Field.Store.YES));
			doc.add(new IntField("metascore", obra.getMetascore(), Field.Store.YES));
			doc.add(new DoubleField("imdb_rating", obra.getImdb_rating(), Field.Store.YES));
			doc.add(new IntField("imdb_votes", obra.getImdb_votes(), Field.Store.YES));
			
			// POCO EFICIENTE
			for (Persona p : personas) {
				if (obra.getId() == p.getId()) {
					 //System.out.println("\t" + p.getNombre());
					 doc.add(new TextField("persona_nombre", p.getNombre(), Field.Store.YES));
					 if (p.getSexo() != null)
						 doc.add(new TextField("persona_sexo", p.getSexo(), Field.Store.YES));
					 if (p.getNacimiento() != null)
						 doc.add(new LongField("persona_nacimiento", p.getNacimiento().getTime(), Field.Store.YES));
					 if (p.getNacionalidad() != null)
						 doc.add(new TextField("persona_nacionalidad", p.getNacionalidad(), Field.Store.YES));
					 if (p.getRol() != null)
						 doc.add(new TextField("persona_rol", p.getRol(), Field.Store.YES));
				}
			}
			
			if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
				// New index, so we just add the document (no old document can be
				// there):
				System.out.println("adding " + obra.getNombre());
				writer.addDocument(doc);
			} else {
				// Existing index (an old copy of this document may have been
				// indexed) so
				// we use updateDocument instead to replace the old one matching the
				// exact
				// path, if present:
				System.out.println("updating " + obra.getNombre());
				writer.updateDocument(new Term("nombre", obra.getNombre()), doc);
			}
		}
	}
}