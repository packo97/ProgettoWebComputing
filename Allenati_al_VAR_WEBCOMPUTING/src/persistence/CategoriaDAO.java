package persistence;

import java.util.ArrayList;

import model.Categoria;
import model.Video;

public interface CategoriaDAO {
	

	public void save(Video video);  // Create
	public ArrayList<Video> findByPrimaryKey(String nome,String email);    // Retrieve
	public ArrayList<Video> findAll();       
	public void update(Categoria categoria); //Update
	public void delete(Video video); //Delete	
	public void deleteUrlCategoria(String url);

}