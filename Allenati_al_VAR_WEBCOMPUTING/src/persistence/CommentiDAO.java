package persistence;

import java.util.ArrayList;

import model.Commenti;
import model.Video;

public interface CommentiDAO {

	public void save(String commento,String url);  // Create
	public Commenti findByPrimaryKey(String url);     // Retrieve
	public ArrayList<Commenti> findAll();       
	public void update(String commento); //Update
	public void delete(String url); //Delete	
}
