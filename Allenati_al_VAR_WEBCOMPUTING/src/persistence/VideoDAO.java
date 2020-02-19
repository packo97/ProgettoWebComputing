package persistence;

import java.util.ArrayList;

import model.Pair;
import model.Video;

public interface VideoDAO {
	
	public void save(Video video);  // Create
	public Video findByPrimaryKey(String url);     // Retrieve
	public ArrayList<Video> findAll();       
	public void update(Video video); //Update
	public void delete(String url); //Delete	
	public boolean esisteVideo(String urlNuovo);
	public boolean esisteNome(String nomeNuovo);
	public boolean esisteNomeModifica(String modificaNome, String url);
	public String getRispostaCorretta(String url);
	public int updateVisualizzazioni(String url);
	public ArrayList<Video> getRicercaPerFiltri(String categoria, int durataMinima, int durataMassima, String periodo, String difficolta, String squadraA, String squadraB, String cercaIn, String piuGiusti, String piuSbagliati, int numeroCommentiMin, int numeroCommentiMax);
	public ArrayList<Video> findByName(String nome);
	public int getPunteggioForCategoria(String email, String categoria);
	public ArrayList<Integer> getPunteggioForCategoriaAndMese(String email, String categoria);
	public ArrayList<Pair> getClassifica(String filtro);
}
