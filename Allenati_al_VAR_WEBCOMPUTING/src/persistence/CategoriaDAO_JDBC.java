package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import model.Categoria;
import model.OpzioniRisposte;
import model.Video;

public class CategoriaDAO_JDBC implements CategoriaDAO{
	
	@Override
	public  ArrayList<Video> findByPrimaryKey(String nomeCategoria, String email) {
		
		Connection connection = null;
		ArrayList<Video> lista_video = new ArrayList<Video>();
		
		try {
			connection = DBManager.getInstance().getConnection();
			
			String query = "SELECT * FROM categoria c JOIN video v ON c.fk_video=v.url JOIN utenti u ON c.fk_utente=u.email WHERE c.nome = ? AND c.fk_utente=?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nomeCategoria);
			statement.setString(2, email);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Video video = new Video();
				video.setId(result.getString("id"));				
				video.setUrl(result.getString("url"));
				video.setNome(result.getString(7)); 
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
		return null;
	}
	

	@Override
	public void update(Categoria categoria) {	
	}
	
	@Override
	public void save(Video video) {
		Connection connection = null;

		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String isPresente = "SELECT * FROM categoria c WHERE c.nome = ? AND fk_video = ? AND fk_utente = ?";
			
			PreparedStatement statementPresente = connection.prepareStatement(isPresente);
			statementPresente.setString(1, "recenti");
			statementPresente.setString(2, video.getUrl());
			statementPresente.setString(3, DBManager.getInstance().getUtenteCorrente().getEmail());
			ResultSet resPresente = statementPresente.executeQuery();
			
			String contaRecenti = "SELECT COUNT(*) FROM categoria WHERE nome = ? GROUP BY nome";
			
			PreparedStatement statementContatore = connection.prepareStatement(contaRecenti);
			statementContatore.setString(1, "recenti");
			
			ResultSet resContatore = statementContatore.executeQuery();
			
			String min_data  = "SELECT min(data) FROM categoria";
			
			PreparedStatement  statementMinData = connection.prepareStatement(min_data);
			ResultSet resMinData = statementMinData.executeQuery();
			resMinData.next();
		
			if(resContatore.next() && resContatore.getInt(1) == 15) {
				
				String delete_recenti = "DELETE FROM categoria WHERE nome = ? AND data = ?";
				
				PreparedStatement statementCancellaMenoRecente = connection.prepareStatement(delete_recenti);
				statementCancellaMenoRecente.setString(1, "recenti");
				statementCancellaMenoRecente.setString(2, resMinData.getString(1));
				statementCancellaMenoRecente.executeUpdate();
			}
			
			if(!resPresente.next()) {
				
				String insert = "INSERT INTO categoria(nome, fk_video, fk_utente, data) VALUES (?,?,?,?)";
				
				PreparedStatement statement = connection.prepareStatement(insert);
				statement.setString(1,"recenti");
				statement.setString(2, video.getUrl());
				statement.setString(3, DBManager.getInstance().getUtenteCorrente().getEmail());
				statement.setString(4, new Date().toLocaleString());
				statement.executeUpdate();
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
	}
	
	@Override
	public void delete(Video video) {
		
		Connection connection = null;
		
		try {
		
			connection = DBManager.getInstance().getConnection();
			
			String delete = "DELETE FROM categoria WHERE fk_video = ? ";
			
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
	
	@Override
	public void deleteUrlCategoria(String url) {
		
		Connection connection = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			PreparedStatement statement;
			statement = connection.prepareStatement("DELETE FROM categoria WHERE fk_video=?");
			statement.setString(1, url);

			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
}