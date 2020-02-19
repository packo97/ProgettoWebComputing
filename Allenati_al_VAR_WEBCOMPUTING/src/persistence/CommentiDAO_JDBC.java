package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import model.Commenti;
import model.Commento;
import model.Utente;


public class CommentiDAO_JDBC implements CommentiDAO {

	@Override
	public void save(String commento, String url) {
		
		Connection connection = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String insert = "INSERT INTO commenti(data,commento,fk_utente, fk_video) VALUES (?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, new Date().toLocaleString());
			statement.setString(2, commento);
			statement.setString(3, DBManager.getInstance().getUtenteCorrente().getEmail());
			statement.setString(4, url);

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
	public Commenti findByPrimaryKey(String id) {
		
		Connection connection = null;
		Commenti commenti = new Commenti();
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String query = "SELECT * FROM commenti c JOIN utenti u ON c.fk_utente=u.email WHERE fk_video = ? ORDER BY c.data";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Commento c = new Commento(result.getString("commento"),result.getString("data"),new Utente(result.getString("nome"),result.getString("cognome")));
				commenti.aggiungiCommento(c);
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
		return commenti;
	}

	@Override
	public ArrayList<Commenti> findAll() {
		return null;
	}

	@Override
	public void update(String commento) {
	}

	@Override
	public void delete(String url) {
		Connection connection = null;
		
		try {
			connection = DBManager.getInstance().getConnection();
			
			PreparedStatement statement;
			statement = connection.prepareStatement("DELETE FROM commenti WHERE fk_video=?");
			statement.setString(1, url);

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
