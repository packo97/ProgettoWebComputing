package persistence;

import java.util.ArrayList;

import model.Video;

public interface PreferitiDAO {

	public void save(Video video);  // Create
	public ArrayList<Video> findByPrimaryKey(String id);     // Retrieve
	public ArrayList<Video> findAll();       
	public void update(Video video); //Update
	public void delete(Video video); //Delete
	public void deleteUrlPreferiti(String url);
	public Video getVideo(String url, String email);
}
