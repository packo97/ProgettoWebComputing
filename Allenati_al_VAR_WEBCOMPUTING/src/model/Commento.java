package model;

public class Commento {

	private String testo;
	private String data;
	private Utente autore;
	
	public Commento(String testo, String data, Utente autore) {
		this.testo = testo;
		this.data = data;
		this.autore = autore;
	}
	
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Utente getAutore() {
		return autore;
	}
	public void setAutore(Utente autore) {
		this.autore = autore;
	}
	
	
}
