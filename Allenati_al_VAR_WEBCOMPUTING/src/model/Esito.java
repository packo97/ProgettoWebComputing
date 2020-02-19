package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Esito {

	private int id;
	private ArrayList<Video> video;
	private Date data;
	private Boolean risultato;
	
	public Esito() {
		
		video = new ArrayList<Video>();
		data = new Date();
		risultato = true;
	}
	
	public Esito(ArrayList<Video> video) {
	
		this.video = video;
		data = new Date();
		risultato = true;
	}
	
	public ArrayList<Video> getVideo() {
		return video;
	}

	public void setVideo(ArrayList<Video> video) {
		this.video = video;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getRisultato() {
		return risultato;
	}

	public void setRisultato(Boolean risultato) {
		this.risultato = risultato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setUrlToVideo(List items) {
		video = new ArrayList<Video>();
		
		for (int i = 0; i < items.size(); i++) {
			video.add(new Video());
			if(i==0) {
				StringBuilder sb = new StringBuilder(items.get(i).toString());
				sb.deleteCharAt(0);
				items.set(i, sb.toString());
			}
			if(i==9) {
				StringBuilder sb = new StringBuilder(items.get(i).toString());
				sb.deleteCharAt(sb.toString().length()-1);
				items.set(i, sb.toString());
			}
			
			video.get(i).setUrl(items.get(i).toString());
			//OpzioniRisposte risposte = new OpzioniRisposte("corretta", "errata");
			//risposte.setRispostaUtente(true);
			//video.get(i).setRisposte(risposte);
			
		}
		
	}
	
	
}
