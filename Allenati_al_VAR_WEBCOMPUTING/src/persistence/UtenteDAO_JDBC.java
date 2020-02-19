package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import model.Utente;


public class UtenteDAO_JDBC implements UtenteDAO{

	@Override
	public void save(Utente utente) {
		
		Connection connection = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String insert = "INSERT INTO utenti(nome, cognome, email, password, amministratore, punteggio, data_registrazione) VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(insert);
			
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());
			statement.setString(3, utente.getEmail());
			statement.setString(4, utente.getPassword());
			statement.setBoolean(5, utente.getAmministratore());
			statement.setInt(6, 0);
			statement.setString(7, new Date().toLocaleString());
			
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
	public Utente findByPrimaryKey(String email, String password) {
		
		Connection connection = null;
		
		Utente utente = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String query = "SELECT * FROM utenti where email = ? AND password = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				
				utente = new Utente();
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setAmministratore(result.getBoolean("amministratore"));
				utente.setPunteggio(result.getInt("punteggio"));
				utente.setDataRegistrazione(result.getString("data_registrazione"));
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
		return utente;
	}

	@Override
	public ArrayList<Utente> findAll() {
		
		Connection connection = null;
		
		ArrayList<Utente> lista_utenti = new ArrayList<Utente>();
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String query = "SELECT * FROM utenti ORDER BY punteggio DESC";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Utente utente = new Utente();
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setAmministratore(result.getBoolean("amministratore"));
				utente.setPunteggio(result.getInt("punteggio"));
				utente.setDataRegistrazione(result.getString("data_registrazione"));
				lista_utenti.add(utente);
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
		return lista_utenti;
	}

	@Override
	public void update(Utente utente) {	
	}

	@Override
	public void delete(Utente utente) {	
	}
	
	@Override
	public boolean esisteUtente(String email) {
		
		Connection connection = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String query = "SELECT email FROM utenti WHERE email = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				return true;
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
		return false;
	}
	
	@Override
	public void updatePunteggio(Utente utente, int punteggio) {
		
		Connection connection = null;
		
		int punteggioPrecedente = 0;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String query = "SELECT punteggio FROM utenti WHERE email=?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, utente.getEmail());
			ResultSet res = statement.executeQuery();
			
			if(res.next()) {
				punteggioPrecedente = res.getInt("punteggio");
			}
			
			String update = "UPDATE utenti SET punteggio=? WHERE email=?";
			
			PreparedStatement statementUpdate = connection.prepareStatement(update);

			statementUpdate.setInt(1, punteggioPrecedente + punteggio);
			statementUpdate.setString(2, utente.getEmail());
			
			statementUpdate.executeUpdate();
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
	public Utente getUtente(String email) {
		
		Connection connection = null;
		
		Utente utente = null;
		
		try {
			
			connection = DBManager.getInstance().getConnection();
			
			String query = "SELECT * FROM utenti WHERE email = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				utente = new Utente();
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setAmministratore(result.getBoolean("amministratore"));
				utente.setPunteggio(result.getInt("punteggio"));
				utente.setDataRegistrazione(result.getString("data_registrazione"));
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
		return utente;
	}
}
