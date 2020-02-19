package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class RisposteUtenteDAO_JDBC implements RisposteUtenteDAO{

	@Override
	public void save(String url, Boolean risposta) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			
			String rispostaPresente = "SELECT * FROM risposte_utente WHERE fk_video=? AND fk_utente=?";
			PreparedStatement statementRispostaPresente = connection.prepareStatement(rispostaPresente);
			statementRispostaPresente.setString(1, url);
			statementRispostaPresente.setString(2, DBManager.getInstance().getUtenteCorrente().getEmail());
			ResultSet result = statementRispostaPresente.executeQuery();
			
			if(!result.next()) {
				String insert = "INSERT INTO risposte_utente(fk_utente, fk_video, valore_risposta) values (?,?,?)";
				PreparedStatement statement = connection.prepareStatement(insert);
				statement.setString(1, DBManager.getInstance().getUtenteCorrente().getEmail());
				statement.setString(2, url);
				statement.setBoolean(3, risposta);
	
				statement.executeUpdate();
			}
			else {
				update(url, risposta);
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
	public ArrayList<Boolean> findByPrimaryKey(String url, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Boolean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String url, Boolean risposta) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String update = "UPDATE risposte_utente SET valore_risposta=? WHERE fk_video=?";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setBoolean(1, risposta);
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
		
	}

	@Override
	public void delete(String url) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			String delete = "DELETE FROM risposte_utente WHERE fk_video = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, url);

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

}
