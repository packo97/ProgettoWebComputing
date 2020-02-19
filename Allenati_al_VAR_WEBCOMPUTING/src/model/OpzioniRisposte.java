package model;

public class OpzioniRisposte {
	
	private Long id;
	private String opzioneCorretta;
	private String opzioneErrata;
	private Boolean rispostaUtente; //true se l'utente ha inserito la risposta corretta
	
	

	public OpzioniRisposte(String opzioneCorretta, String opzioneErrata, Boolean rispostaUtente) {
		
		this.opzioneCorretta = opzioneCorretta;
		this.opzioneErrata = opzioneErrata;
		this.rispostaUtente = rispostaUtente;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOpzioneCorretta(String opzioneCorretta) {
		this.opzioneCorretta = opzioneCorretta;
	}

	public void setOpzioneErrata(String opzioneErrata) {
		this.opzioneErrata = opzioneErrata;
	}
	
	public String getOpzioneCorretta() {
		return opzioneCorretta;
	}
	
	public String getOpzioneErrata() {
		return opzioneErrata;
	}
	public Boolean getRispostaUtente() {
		return rispostaUtente;
	}

	public void setRispostaUtente(Boolean rispostaUtente) {
		this.rispostaUtente = rispostaUtente;
	}
}
