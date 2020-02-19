package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Categoria;
import model.OpzioniRisposte;
import model.Video;
import model.Video;

public class PreferitiDAO_JDBC implements PreferitiDAO{

	@Override
	public void save(Video video) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			String insert = "insert into preferiti(fk_video, fk_utente) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, video.getUrl());
			statement.setString(2, DBManager.getInstance().getUtenteCorrente().getEmail());

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
	public ArrayList<Video> findByPrimaryKey(String email) {
		Connection connection = null;
		ArrayList<Video> lista_video = new ArrayList<Video>();
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;

			String query = "SELECT * FROM preferiti p JOIN video v ON p.fk_video=v.url WHERE p.fk_utente = ?";

			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Video video = new Video();
				video.setId(result.getString("id"));				
				video.setUrl(result.getString("url"));
				video.setNome(result.getString("nome"));
				video.setDescrizione(result.getString("descrizione"));
				video.setDifficolta(result.getString("difficolta"));
				video.setVisualizzazioni(result.getInt("visualizzazioni"));
				video.setRisposte(new OpzioniRisposte(result.getString("rispostaCorretta"), result.getString("rispostaErrata"), null));
				video.setCategoria(new Categoria(result.getString("categoria")));
				video.setCommenti(DBManager.getInstance().getCommentiDAO().findByPrimaryKey(result.getString("url")));
				
				lista_video.add(video);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}	
		return lista_video;
	}

	@Override
	public ArrayList<Video> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Video preferiti) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Video video) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			String delete = "DELETE FROM preferiti WHERE fk_video = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, video.getUrl());

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
	
	//metodi di utilità
	
	public Video getVideo(String url, String email) {
		Connection connection = null;
		Video video = null;
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "select * from preferiti p LEFT OUTER JOIN video v ON p.fk_video=v.url where fk_utente = ? AND fk_video = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, url);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				video = new Video();
				video.setId(result.getString("id"));				
				video.setUrl(result.getString("url"));
				video.setNome(result.getString("nome"));
				video.setDescrizione(result.getString("descrizione"));
				video.setDifficolta(result.getString("difficolta"));
				video.setVisualizzazioni(result.getInt("visualizzazioni"));
				video.setRisposte(new OpzioniRisposte(result.getString("rispostaCorretta"), result.getString("rispostaErrata"), null));
				video.setCategoria(new Categoria(result.getString("categoria")));
				video.setCommenti(DBManager.getInstance().getCommentiDAO().findByPrimaryKey(result.getString("url")));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}	
		return video;
	}

	public void deleteUrlPreferiti(String url) {
		Connection connection = null;
		
		try {
			connection = DBManager.getInstance().getConnection();
			
			PreparedStatement statement;
			statement = connection.prepareStatement("DELETE FROM preferiti WHERE fk_video=?");
			statement.setString(1, url);

			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

}
