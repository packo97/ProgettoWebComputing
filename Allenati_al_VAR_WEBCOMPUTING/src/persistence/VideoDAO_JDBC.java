package persistence;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import model.Categoria;
import model.OpzioniRisposte;
import model.Pair;
import model.Video;



public class VideoDAO_JDBC implements VideoDAO{

	@Override
	public Video findByPrimaryKey(String url) {
		
		Connection connection = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			Video video = new Video();
			
			String query = "SELECT * FROM video v WHERE v.url = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, url);
			
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				
				video.setId(result.getString("id"));				
				video.setUrl(result.getString("url"));
				video.setNome(result.getString("nome"));			
				video.setRisposte(new OpzioniRisposte(result.getString("rispostaCorretta"), result.getString("rispostaErrata"), null));				
				video.setDifficolta(result.getString("difficolta"));
				video.setCategoria(new Categoria(result.getString("categoria")));
				return video;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}
	
	@Override
	public ArrayList<Video> findAll() {
		
		Connection connection = null;
		ArrayList<Video> lista_video = new ArrayList<Video>();
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			Video video = null;
			PreparedStatement statement;
			String query_findAll = "SELECT * FROM video v";
			statement = connection.prepareStatement(query_findAll);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				video = new Video();
				video.setId(result.getString("id"));				
				video.setUrl(result.getString("url"));
				video.setNome(result.getString("nome"));
				video.setDescrizione(new String(result.getString("descrizione").getBytes(),"UTF-8"));
				video.setDifficolta(result.getString("difficolta"));
				video.setVisualizzazioni(result.getInt("visualizzazioni"));
				video.setRisposte(new OpzioniRisposte(result.getString("rispostaCorretta"), result.getString("rispostaErrata"), null));
				video.setCategoria(new Categoria(result.getString("categoria")));
				
				lista_video.add(video);
			}
		} catch (SQLException | UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return lista_video;
	}
	
	@Override
	public void save(Video video) {
		
		Connection connection = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();

			String insert = "INSERT INTO video(id, url, nome, descrizione, difficolta, visualizzazioni,rispostaCorretta, rispostaErrata, categoria, durata, data, squadra_a, squadra_b) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, video.getId());
			statement.setString(2, video.getUrl());
			statement.setString(3, video.getNome());
			statement.setString(4, video.getDescrizione());
			statement.setString(5, video.getDifficolta());
			statement.setInt(6, 0);
			statement.setString(7, video.getRisposte().getOpzioneCorretta());
			statement.setString(8, video.getRisposte().getOpzioneErrata());
			statement.setString(9, video.getCategoria().get(0).getNome());
			statement.setInt(10, video.getDurata());
			statement.setString(11, new Date().toInstant().toString());
			statement.setString(12, video.getSquadraA());
			statement.setString(13, video.getSquadraB());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}	
}
	
	@Override
	public void update(Video video) {
		
		Connection connection = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();

			String insert = "UPDATE video SET nome=?, descrizione=?, difficolta=?, rispostacorretta=?, rispostaerrata=?, categoria=?, durata=?, squadra_a=?, squadra_b=? WHERE url=?";
			PreparedStatement statement = connection.prepareStatement(insert);

			
			statement.setString(1, video.getNome());
		
			statement.setString(2, video.getDescrizione());
			statement.setString(3, video.getDifficolta());
			statement.setString(4, video.getRisposte().getOpzioneCorretta());
			statement.setString(5, video.getRisposte().getOpzioneErrata());
			statement.setString(6, video.getCategoria().get(0).getNome());
			statement.setInt(7, video.getDurata());
			statement.setString(8, video.getSquadraA());
			statement.setString(9, video.getSquadraB());
			statement.setString(10, video.getUrl());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	@Override
	public void delete(String url) {
		Connection connection = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			PreparedStatement statement;
			statement = connection.prepareStatement("DELETE FROM video WHERE url=?");
			statement.setString(1, url);

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

	public boolean esisteVideo(String urlNuovo) {
		
		Connection connection = null;
	
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			PreparedStatement statement;
			statement = connection.prepareStatement("SELECT nome FROM video WHERE url=?");
			statement.setString(1, urlNuovo);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
}

	public boolean esisteNome(String nomeNuovo) {
		
		Connection connection = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			PreparedStatement statement;
			statement = connection.prepareStatement("SELECT * FROM video WHERE nome=?");
			statement.setString(1, nomeNuovo);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
}

	@Override
	public boolean esisteNomeModifica(String modificaNome, String url) {
		
		Connection connection = null;
		
		try {
			connection = DBManager.getInstance().getConnection();
			
			PreparedStatement statement;
			statement = connection.prepareStatement("SELECT * FROM video WHERE nome=? AND url!=?");
			statement.setString(1, modificaNome);
			statement.setString(2, url);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
}
	
	@Override
	public String getRispostaCorretta(String url) {
		
		Connection connection = null;
		
		String risposta_corretta = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String query_risposta_corretta = "SELECT * FROM video WHERE url=?";
			PreparedStatement statement = connection.prepareStatement(query_risposta_corretta);
			statement.setString(1, url);
			ResultSet result = statement.executeQuery();
			
			if(result.next()){
				risposta_corretta = result.getString("rispostacorretta");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}		
		return risposta_corretta;
	}
	
	public int updateVisualizzazioni(String url) {
		
		Connection connection = null;
		int visualizzazioni = 0;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String numeroVisualizzazioni = "SELECT visualizzazioni FROM video WHERE url = ?";
			
			PreparedStatement statementNumeroVisualizzazioni = connection.prepareStatement(numeroVisualizzazioni);
			statementNumeroVisualizzazioni.setString(1, url);
			
			ResultSet resNumeroVisualizzazioni = statementNumeroVisualizzazioni.executeQuery();
			
			if(resNumeroVisualizzazioni.next()) {
				visualizzazioni = resNumeroVisualizzazioni.getInt(1);
			}
			
			String update = "UPDATE video SET visualizzazioni = ? WHERE url=?";
			PreparedStatement statement = connection.prepareStatement(update);
			
			statement.setInt(1, ++visualizzazioni );
			statement.setString(2, url);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return visualizzazioni;
	}

	@Override
	public ArrayList<Video> getRicercaPerFiltri(String categoria, int durataMinima, int durataMassima, String periodo, String difficolta, String squadraA, String squadraB, String cercaIn, String piuGiusti, String piuSbagliati, int numeroCommentiMin, int numeroCommentiMax) {
		
		Connection connection = null;
		ArrayList<Video> lista_video = new ArrayList<Video>();
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			Video video = null;
			
			int contFiltri = 2;
			ArrayList<Pair> filtri = new ArrayList<Pair>();
			String query_ricerca_per_filtri;
			
			
			if(piuGiusti.equals("true")) {
				query_ricerca_per_filtri = "SELECT COUNT(valore_risposta) AS numero_risposte_giuste, COUNT(commento) AS numero_commenti, v.url AS fk_video_risposte, v.nome FROM risposte_utente r LEFT OUTER JOIN video v ON v.url=r.fk_video LEFT OUTER JOIN commenti c ON v.url=c.fk_video WHERE durata BETWEEN ? AND ? ";
				if(cercaIn.equals("prove_di_autovalutazione")) {
					filtri.add(new Pair("AND risposta_utente=?","true"));
				}
				else
					filtri.add(new Pair("AND valore_risposta=?","true"));
			}
			else if(piuSbagliati.equals("true")) {
				query_ricerca_per_filtri = "SELECT COUNT(valore_risposta) AS numero_risposte_giuste, COUNT(commento) AS numero_commenti, v.url AS fk_video_risposte, v.nome FROM risposte_utente r LEFT OUTER JOIN video v ON v.url=r.fk_video LEFT OUTER JOIN commenti c ON v.url=c.fk_video WHERE durata BETWEEN ? AND ? ";
				if(cercaIn.equals("prove_di_autovalutazione")) {
					filtri.add(new Pair("AND risposta_utente=?","false"));
				}
				else
					filtri.add(new Pair("AND valore_risposta=?","false"));
			}
			else if(cercaIn.equals("risposte_utente")) {
				query_ricerca_per_filtri = "SELECT COUNT(valore_risposta) AS numero_risposte_giuste, COUNT(commento) AS numero_commenti, v.url AS fk_video_risposte, v.nome FROM video v LEFT OUTER JOIN commenti c ON v.url=c.fk_video JOIN risposte_utente r ON v.url=r.fk_video WHERE durata BETWEEN ? AND ?";
			}
			else
				query_ricerca_per_filtri = "SELECT COUNT(commento) AS numero_commenti, v.url AS fk_video_risposte, v.nome FROM video v LEFT OUTER JOIN commenti c ON v.url=c.fk_video WHERE durata BETWEEN ? AND ?";

			
			if(cercaIn.equals("preferiti")) {
				query_ricerca_per_filtri = query_ricerca_per_filtri.replace("WHERE", "JOIN preferiti p ON v.url=p.fk_video WHERE");
			}
			else if(cercaIn.equals("prove_di_autovalutazione")) {
				query_ricerca_per_filtri = query_ricerca_per_filtri.replace("valore_risposta", "risposta_utente");
				query_ricerca_per_filtri = query_ricerca_per_filtri.replace("risposte_utente r LEFT OUTER JOIN video v ON v.url=r.fk_video", "video v");
				query_ricerca_per_filtri = query_ricerca_per_filtri.replace("WHERE", "JOIN esiti e ON v.url=e.fk_video WHERE");
			}
			
			if(cercaIn.equals("risposte_utente")) {
				filtri.add(new Pair("AND r.fk_utente=?",DBManager.getInstance().getUtenteCorrente().getEmail()));		
			}
			else if(cercaIn.equals("preferiti")) {
				filtri.add(new Pair("AND p.fk_utente=?",DBManager.getInstance().getUtenteCorrente().getEmail()));		
			}
			else if(cercaIn.equals("prove_di_autovalutazione")) {
				filtri.add(new Pair("AND e.fk_utente=?",DBManager.getInstance().getUtenteCorrente().getEmail()));		
			}
			
			if(!categoria.equals("")) {
				filtri.add(new Pair("AND categoria = ?",categoria));
			}
			
			if(!periodo.equals("")) {
				filtri.add(new Pair(" AND v.data BETWEEN ? AND ?",periodo));
				filtri.add(new Pair("",new Date().toInstant().toString()));
			}
			
			if(!difficolta.equals("")) {
				filtri.add(new Pair(" AND difficolta = ?",difficolta));
			}
			
			if(!squadraA.equals("")) {
				filtri.add(new Pair(" AND squadra_a = ?",squadraA));
			}
			
			if(!squadraB.equals("")) {
				filtri.add(new Pair(" AND squadra_b = ?",squadraB));
			}
			
			if(piuGiusti.equals("true") || piuSbagliati.equals("true")) {
				filtri.add(new Pair(" GROUP BY v.url HAVING COUNT(commento) BETWEEN " + numeroCommentiMin + " AND " + numeroCommentiMax + " ORDER BY numero_risposte_giuste DESC",""));
			}
			else if(cercaIn.equals("prove_di_autovalutazione")) {
				filtri.add(new Pair(" GROUP BY v.url HAVING COUNT(commento) BETWEEN " + numeroCommentiMin + " AND " + numeroCommentiMax ,""));
			}
			else {
				filtri.add(new Pair(" GROUP BY v.url HAVING COUNT(commento) BETWEEN "+ numeroCommentiMin + " AND " + numeroCommentiMax,""));
			}
			
			for (Pair filtro : filtri) {
				query_ricerca_per_filtri+= filtro.getFirst();
			}

			PreparedStatement statement = connection.prepareStatement(query_ricerca_per_filtri);
			statement.setInt(1, durataMinima);
			statement.setInt(2, durataMassima);

			for (Pair filtro : filtri) {
				if(!filtro.getSecond().equals(""))
					statement.setString(++contFiltri, filtro.getSecond().toString());
			}
			
			ResultSet result = statement.executeQuery();
			
			if((piuGiusti.equals("true") || piuSbagliati.equals("true")) && result.next()) {
				int massimoRisposteCorrette = result.getInt(1);
				video = new Video();				
				video.setUrl(result.getString("fk_video_risposte"));
				video.setNome(result.getString("nome"));
				lista_video.add(video);

				while(result.next()) {
					if(result.getInt("numero_commenti") >= massimoRisposteCorrette * 0.2) {
	
						video = new Video();				
						video.setUrl(result.getString("fk_video_risposte"));
						video.setNome(result.getString("nome"));
				
						lista_video.add(video);
					}
				}
			}
			else {
				
				while (result.next()) {
					video = new Video();			
					video.setUrl(result.getString("fk_video_risposte"));
					video.setNome(result.getString("nome"));
			
					lista_video.add(video);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return lista_video;
	}

	@Override
	public ArrayList<Video> findByName(String nome) {
		
		Connection connection = null;
		ArrayList<Video> lista_video = new ArrayList<Video>();
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String query = "SELECT * FROM video v WHERE UPPER(v.nome) LIKE UPPER(CONCAT('%', ?, '%'))";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nome);
		
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Video video = new Video();
				video.setId(result.getString("id"));				
				video.setUrl(result.getString("url"));
				video.setNome(result.getString("nome"));			
				video.setRisposte(new OpzioniRisposte(result.getString("rispostaCorretta"), result.getString("rispostaErrata"), null));				
						
				lista_video.add(video);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return lista_video;
	}

	@Override
	public int getPunteggioForCategoria(String email, String categoria) {
		
		Connection connection = null;
		int punteggio = 0;
		int moltiplicatore = 1;
		
		try {
			
			connection = DBManager.getInstance().getConnection();

			String query = "SELECT * FROM video v JOIN esiti e ON v.url=e.fk_video WHERE e.fk_utente=? AND v.categoria=?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, categoria);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				if(result.getString("risposta_utente").equals("true")) {
					if(result.getString("difficolta").equals("FACILE")) {
						moltiplicatore = 1;
					}
					else if(result.getString("difficolta").equals("NORMALE")) {
						moltiplicatore = 2;
					}
					else if(result.getString("difficolta").equals("DIFFICILE")) {
						moltiplicatore = 3;
					}
					
					ArrayList<String> storicoRisposte = DBManager.getInstance().getEsitoDAO().getRisposte(email, result.getString("url"), "true");
					
					if(storicoRisposte.isEmpty())
						punteggio+= 25 * moltiplicatore;
					else if(storicoRisposte.size() <= 10)
						punteggio+= 15 * moltiplicatore;
					else if(storicoRisposte.size() <= 50)
						punteggio+= 5 * moltiplicatore;
					else
						punteggio+= 0 * moltiplicatore;
						
				}
				else if(result.getString("risposta_utente").equals("false")) {
					
					if(result.getString("difficolta").equals("FACILE")) {
						moltiplicatore = 3;
					}
					else if(result.getString("difficolta").equals("NORMALE")) {
						moltiplicatore = 2;
					}
					else if(result.getString("difficolta").equals("DIFFICILE")) {
						moltiplicatore = 1;
					}
					
					ArrayList<String> storicoRisposte = DBManager.getInstance().getEsitoDAO().getRisposte(email, result.getString("url"), "false");
					
					if(storicoRisposte.isEmpty())
						punteggio-= 5 * moltiplicatore;
					else if(storicoRisposte.size()<=10)
						punteggio-= 15 * moltiplicatore;
					else if(storicoRisposte.size()<=50)
						punteggio-= 25 * moltiplicatore;
					else
						punteggio-= 50 * moltiplicatore;
				}	
			}	
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return punteggio;
	}
	
	@Override
	public ArrayList<Integer> getPunteggioForCategoriaAndMese(String email, String categoria) {
		
		Connection connection = null;
		ArrayList<Integer> punteggiPerMese = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0));

		int punteggio = 0;
		int moltiplicatore = 1;
		
		try {
			
			connection = DBManager.getInstance().getConnection();

			String query = "SELECT * FROM video v JOIN esiti e ON v.url=e.fk_video WHERE e.fk_utente=? AND v.categoria=?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, categoria);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				if(result.getString("risposta_utente").equals("true")) {
					if(result.getString("difficolta").equals("FACILE")) {
						moltiplicatore = 1;
					}
					else if(result.getString("difficolta").equals("NORMALE")) {
						moltiplicatore = 2;
					}
					else if(result.getString("difficolta").equals("DIFFICILE")) {
						moltiplicatore = 3;
					}
						
					ArrayList<String> storicoRisposte = DBManager.getInstance().getEsitoDAO().getRisposte(email, result.getString("url"), "true");
					
					if(storicoRisposte.isEmpty())
						punteggio+= 25 * moltiplicatore;
					else if(storicoRisposte.size() <= 10)
						punteggio+= 15 * moltiplicatore;
					else if(storicoRisposte.size() <= 50)
						punteggio+= 5 * moltiplicatore;
					else
						punteggio+= 0 * moltiplicatore;
				}
				else if(result.getString("risposta_utente").equals("false")) {
					
					if(result.getString("difficolta").equals("FACILE")) {
						moltiplicatore = 3;
					}
					else if(result.getString("difficolta").equals("NORMALE")) {
						moltiplicatore = 2;
					}
					else if(result.getString("difficolta").equals("DIFFICILE")) {
						moltiplicatore = 1;
					}
					
					ArrayList<String> storicoRisposte = DBManager.getInstance().getEsitoDAO().getRisposte(email, result.getString("url"), "false");
					
					if(storicoRisposte.isEmpty())
						punteggio-= 5 * moltiplicatore;
					else if(storicoRisposte.size()<=10)
						punteggio-= 15 * moltiplicatore;
					else if(storicoRisposte.size()<=50)
						punteggio-= 25 * moltiplicatore;
					else
						punteggio-= 50 * moltiplicatore;		
				}
				
				if(result.getString("data").charAt(5)=='0' && result.getString("data").charAt(6)=='1') {
					punteggiPerMese.set(0, punteggiPerMese.get(0) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='0' && result.getString("data").charAt(6)=='2') {
					punteggiPerMese.set(0, punteggiPerMese.get(1) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='0' && result.getString("data").charAt(6)=='3') {
					punteggiPerMese.set(0, punteggiPerMese.get(2) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='0' && result.getString("data").charAt(6)=='4') {
					punteggiPerMese.set(0, punteggiPerMese.get(3) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='0' && result.getString("data").charAt(6)=='5') {
					punteggiPerMese.set(0, punteggiPerMese.get(4) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='0' && result.getString("data").charAt(6)=='6') {
					punteggiPerMese.set(0, punteggiPerMese.get(5) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='0' && result.getString("data").charAt(6)=='7') {
					punteggiPerMese.set(0, punteggiPerMese.get(6) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='0' && result.getString("data").charAt(6)=='8') {
					punteggiPerMese.set(0, punteggiPerMese.get(7) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='0' && result.getString("data").charAt(6)=='9') {
					punteggiPerMese.set(0, punteggiPerMese.get(8) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='1' && result.getString("data").charAt(6)=='0') {
					punteggiPerMese.set(0, punteggiPerMese.get(9) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='1' && result.getString("data").charAt(6)=='1') {
					punteggiPerMese.set(0, punteggiPerMese.get(10) + punteggio);
				}
				else if(result.getString("data").charAt(5)=='1' && result.getString("data").charAt(6)=='2') {
					punteggiPerMese.set(0, punteggiPerMese.get(11) + punteggio);
				}
			}		
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return punteggiPerMese;
	}

	
	@Override
	public ArrayList<Pair> getClassifica(String filtro) {
		
		Connection connection = null;
		ArrayList<Pair> classifica_video = new ArrayList<Pair>();
	
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String query = "SELECT e.fk_video, COUNT(*) AS numero_risposte_corrette, v.id, v.url, v.nome, v.difficolta, v.categoria  FROM video v JOIN esiti e ON v.url=e.fk_video WHERE e.risposta_utente=? GROUP BY e.fk_video, v.id, v.url, v.nome, v.difficolta, v.categoria ORDER BY numero_risposte_corrette DESC";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, filtro);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Video video = new Video();
				video.setId(result.getString("id"));				
				video.setUrl(result.getString("url"));
				video.setNome(result.getString("nome"));						
				video.setDifficolta(result.getString("difficolta"));
				video.setCategoria(new Categoria(result.getString("categoria")));
				classifica_video.add(new Pair(video,result.getInt("numero_risposte_corrette")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return classifica_video;
	}
}
