package persistence;

import java.util.ArrayList;

public interface RisposteUtenteDAO {
	
	public void save(String url, Boolean risposta); 
	public ArrayList<Boolean> findByPrimaryKey(String url, String email);    
	public ArrayList<Boolean> findAll();       
	public void update(String url, Boolean risposta); 
	public void delete(String url); 
}
