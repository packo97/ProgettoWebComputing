package persistence;

import java.util.ArrayList;
import model.Utente;

public interface UtenteDAO {

	public void save(Utente utente); 
	public Utente findByPrimaryKey(String id, String password);  
	public ArrayList<Utente> findAll();       
	public void update(Utente utente); 
	public void delete(Utente utente); 
	public boolean esisteUtente(String email);
	public void updatePunteggio(Utente utente, int punteggio);
	public Utente getUtente(String email);
}
