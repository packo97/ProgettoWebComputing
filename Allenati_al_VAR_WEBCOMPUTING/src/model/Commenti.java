package model;

import java.util.ArrayList;
import java.util.Date;

import persistence.DBManager;

public class Commenti {

	private Long id;
	private ArrayList<Commento> lista_commenti;
	
	public Commenti() {
		lista_commenti = new ArrayList<Commento>();
	}
	
	public ArrayList<Commento> getLista_commenti() {
		return lista_commenti;
	}
	
	public void aggiungiCommento(Commento c) {
		lista_commenti.add(c);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
