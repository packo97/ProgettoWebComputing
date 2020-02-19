package model;

import java.util.ArrayList;

import persistence.DBManager;

public class Video {

	private String id;
	private String url;
	private String nome;
	private String descrizione;
	private String difficolta;
	private int visualizzazioni;
	private ArrayList<Categoria> categorie;
	private Commenti commenti;
	private OpzioniRisposte risposte;
	private String squadraA;
	private String squadraB;
	private int durata;


	public Video(String id,String url, String nome, String descrizione, String difficolta, Categoria categoria, OpzioniRisposte risposte) {
		this.id = id;
		this.url = url;
		this.nome = nome;
		this.descrizione = descrizione;
		this.difficolta = difficolta;
		this.visualizzazioni = 0;
		this.categorie = new ArrayList<Categoria>();
		this.categorie.add(categoria);
		this.commenti = new Commenti();
		this.risposte = risposte;
		
	}
	
	public Video() {
		this.categorie = new ArrayList<Categoria>();
		this.commenti = new Commenti();
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDifficolta() {
		return difficolta;
	}

	public void setDifficolta(String difficolta) {
		this.difficolta = difficolta;
	}
	
	public int getVisualizzazioni() {
		return DBManager.getInstance().getVideoDAO().updateVisualizzazioni(url);
	}

	public void setVisualizzazioni(int visualizzazioni) {
		this.visualizzazioni = visualizzazioni;
	}

	public ArrayList<Categoria> getCategoria() {
		return categorie;
	}

	public void setCategoria(ArrayList<Categoria> categorie) {
		this.categorie = categorie;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categorie.add(categoria);
	}

	public Commenti getCommenti() {
		return commenti;
	}

	public void setCommenti(Commenti commenti) {
		this.commenti = commenti;
	}

	public OpzioniRisposte getRisposte() {
		return risposte;
	}

	public void setRisposte(OpzioniRisposte risposte) {
		this.risposte = risposte;
	}
	

	public Boolean isPreferito() {
		return DBManager.getInstance().getUtenti().get(0).getPreferiti().contains(this);
	}

	public String getSquadraA() {
		return squadraA;
	}

	public void setSquadraA(String squadraA) {
		this.squadraA = squadraA;
	}

	public String getSquadraB() {
		return squadraB;
	}

	public void setSquadraB(String squadraB) {
		this.squadraB = squadraB;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}
}
