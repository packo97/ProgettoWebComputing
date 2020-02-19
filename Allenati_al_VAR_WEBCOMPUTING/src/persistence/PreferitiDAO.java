package persistence;

import java.util.ArrayList;

import model.Video;

public interface PreferitiDAO {

	public void save(Video video);
	public ArrayList<Video> findByPrimaryKey(String id);   
	public ArrayList<Video> findAll();       
	public void update(Video video); 
	public void delete(Video video);
	public void deleteUrlPreferiti(String url);
	public Video getVideo(String url, String email);
}
