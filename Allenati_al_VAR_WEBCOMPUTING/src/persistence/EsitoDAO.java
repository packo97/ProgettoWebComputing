package persistence;

import java.util.ArrayList;

import model.Esito;
import model.Video;

public interface EsitoDAO {

	public void save(Esito esito);  // Create
	public ArrayList<Esito> findByPrimaryKey(String email);     // Retrieve
	public ArrayList<Esito> findAll();       
	public void update(Esito esito); //Update
	public void delete(String url); //Delete	
	public ArrayList<Video> getEsito(String email, int id_esito);
	public ArrayList<String> getRisposte(String email, String url, String valore);
	public int getNumeroProvePerRisultato(String email, String risultato);
}
