package persistence;

import java.util.ArrayList;

import model.Categoria;
import model.Video;

public interface CategoriaDAO {
	
	public void save(Video video);  
	public ArrayList<Video> findByPrimaryKey(String nome,String email);   
	public ArrayList<Video> findAll();       
	public void update(Categoria categoria);
	public void delete(Video video); 
	public void deleteUrlCategoria(String url);
}