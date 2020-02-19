package persistence;

import java.util.ArrayList;
import model.Commenti;


public interface CommentiDAO {

	public void save(String commento,String url);  
	public Commenti findByPrimaryKey(String url);     
	public ArrayList<Commenti> findAll();       
	public void update(String commento); 
	public void delete(String url);
}
