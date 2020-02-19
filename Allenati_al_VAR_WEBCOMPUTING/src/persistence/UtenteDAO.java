package persistence;

import java.util.ArrayList;

import model.Utente;

public interface UtenteDAO {

	public void save(Utente utente);  // Create
	public Utente findByPrimaryKey(String id, String password);     // Retrieve
	public ArrayList<Utente> findAll();       
	public void update(Utente utente); //Update
	public void delete(Utente utente); //Delete	
	public boolean esisteUtente(String email);
	public void updatePunteggio(Utente utente, int punteggio);
	public Utente getUtente(String email);
}
