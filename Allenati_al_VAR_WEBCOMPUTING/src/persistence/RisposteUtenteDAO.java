package persistence;

import java.util.ArrayList;

public interface RisposteUtenteDAO {
	
	public void save(String url, Boolean risposta);  // Create
	public ArrayList<Boolean> findByPrimaryKey(String url, String email);    // Retrieve
	public ArrayList<Boolean> findAll();       
	public void update(String url, Boolean risposta); //Update
	public void delete(String url); //Delete	
}
