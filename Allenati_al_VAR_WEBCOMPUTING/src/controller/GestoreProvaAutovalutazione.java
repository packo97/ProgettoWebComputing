package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import model.OpzioniRisposte;
import model.Esito;
import model.Video;
import persistence.DBManager;

public class GestoreProvaAutovalutazione extends HttpServlet {

	private ArrayList<Video> videoProva = new ArrayList<Video>();
	private ArrayList<Video> lista_video_con_risposta_utente = new ArrayList<Video>();
	private Random random = new Random();
	private int risposteErrate;
	private ArrayList<Video> video_nel_db = new ArrayList<Video>();
	static private ArrayList<Video> video_selezionati = new ArrayList<Video>();
	static private ArrayList<ArrayList<Video>> stati = new ArrayList<ArrayList<Video>>();
	private int posizioneStati = 0;
	private int dimesione_prova;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("prova_autovalutazione.jsp");	
		if (req.getParameter("standard") != null && req.getParameter("standard").equals("true")) {
			lista_video_con_risposta_utente.clear();
			risposteErrate = 0;
			video_nel_db = DBManager.getInstance().getVideo();
			
			
			if(video_nel_db.size()<10) {
				rd = req.getRequestDispatcher("error_page.html");
				rd.forward(req, resp);
				return;
			}
			
			if(videoProva.isEmpty())
				while (videoProva.size() < 10) {
					int indice = random.nextInt(video_nel_db.size());
					while (videoProva.contains(video_nel_db.get(indice))) {
						indice = random.nextInt(video_nel_db.size());
					}
					
					videoProva.add(video_nel_db.get(indice));
				}
			dimesione_prova = videoProva.size();
			req.getSession().setAttribute("videoProva", videoProva);
			req.getSession().setAttribute("dimensione", videoProva.size());
		}
		else if (req.getParameter("termina") != null && req.getParameter("termina").equals("true")) {
					
					
			
					while(lista_video_con_risposta_utente.size() < dimesione_prova) {
		
						videoProva.get(0).getRisposte().setRispostaUtente(false);
						lista_video_con_risposta_utente.add(videoProva.get(0));
						videoProva.remove(0);
					}
					
					if (videoProva.isEmpty()) {
						
						int punteggio = 0;
						Esito esito = new Esito(lista_video_con_risposta_utente);
						for (Video video : lista_video_con_risposta_utente) {
							punteggio += calcolaPunteggio(video);	
						}
						if(risposteErrate >= (dimesione_prova * 0.6))
							esito.setRisultato(false);
						
						DBManager.getInstance().getUtenteDAO().updatePunteggio(DBManager.getInstance().getUtenteCorrente(), punteggio);
						DBManager.getInstance().aggiungiAlloStorico(esito);
						rd = req.getRequestDispatcher("esito.jsp");
						req.getSession().setAttribute("esito", lista_video_con_risposta_utente);
					}
		}
		else if (req.getParameter("editata") != null && req.getParameter("editata").equals("true")) {
			lista_video_con_risposta_utente.clear();
			risposteErrate = 0;
			dimesione_prova = videoProva.size();
			req.getSession().setAttribute("videoProva", videoProva);
			req.getSession().setAttribute("dimensione", videoProva.size());
		} 
		else {
			
			if (req.getParameter("risposta").equals(DBManager.getInstance().getVideoDAO().getRispostaCorretta(videoProva.get(0).getUrl())))
				videoProva.get(0).getRisposte().setRispostaUtente(true);
			else
				videoProva.get(0).getRisposte().setRispostaUtente(false);

			lista_video_con_risposta_utente.add(videoProva.get(0));
			
			videoProva.remove(0);

			if (videoProva.isEmpty()) {
				int punteggio = 0;
				Esito esito = new Esito(lista_video_con_risposta_utente);
				for (Video video : lista_video_con_risposta_utente) {
					punteggio += calcolaPunteggio(video);		
				}
				if(risposteErrate >= (dimesione_prova * 0.6))
					esito.setRisultato(false);
				
				DBManager.getInstance().getUtenteDAO().updatePunteggio(DBManager.getInstance().getUtenteCorrente(), punteggio);
				DBManager.getInstance().aggiungiAlloStorico(esito);
				rd = req.getRequestDispatcher("esito.jsp");
				req.getSession().setAttribute("esito", lista_video_con_risposta_utente);
			}

		}
		req.getSession().setAttribute("ordineRisposte", new Random().nextInt(2));
		req.getSession().setAttribute("indice", lista_video_con_risposta_utente.size()+1);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));		
		String line = reader.readLine();
		while (line != null){
			jsonReceived.append(line);
			line = reader.readLine();
		}		
		
		try {
			JSONObject json = new JSONObject(jsonReceived.toString());				
			
			if(json.getString("azione").equals("crea_prova_autovalutazione")) {
				
				
				JSONArray url = new JSONArray(json.getString("url"));
				lista_video_con_risposta_utente.clear();
				videoProva.clear();
				for(int i=0; i<url.length(); i++) {
					videoProva.add(DBManager.getInstance().getVideoDAO().findByPrimaryKey(url.getString(i)));
				}
				
				if(videoProva.size()>=1) {
					
					PrintWriter out = resp.getWriter(); //per mandare il data
					out.println(json.toString()); //mando il data
					out.flush();
			
				}
			}
			else if(json.getString("azione").equals("aggiungi_selezionati")) {
				
				JSONArray url_selezionati = new JSONArray(json.getString("url_selezionati"));
				
				
				for(int i=0; i<url_selezionati.length(); i++) {
					boolean presente = false;
					for(int j=0; j<video_selezionati.size(); j++) {
						if(url_selezionati.get(i).equals(video_selezionati.get(j).getUrl())) {
							presente = true;
						}
					}
					
					if(!presente)
						video_selezionati.add(DBManager.getInstance().getVideoDAO().findByPrimaryKey(url_selezionati.getString(i)));
					
				}

				stati.add(new ArrayList<Video>(video_selezionati));
				posizioneStati = stati.size()-1;
				String jsonRoleAccess = new Gson().toJson(video_selezionati);
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(jsonRoleAccess.toString()); //mando il data
				out.flush();
				
			}
			else if(json.getString("azione").equals("aggiungi_droppati")) {
				
				String url_droppato = json.getString("url_droppato");
				
				boolean presente = false;
				for(int i = 0; i < video_selezionati.size(); i++) {
					if(video_selezionati.get(i).getUrl().equals(url_droppato))
						presente = true;
				}
				
				if(!presente)
					video_selezionati.add(DBManager.getInstance().getVideoDAO().findByPrimaryKey(url_droppato));
				
				
				
				stati.add(new ArrayList<Video>(video_selezionati));
				posizioneStati = stati.size()-1;
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(json.toString()); //mando il data
				out.flush();
				
			}
			else if(json.getString("azione").equals("rimuovi_selezionati")) {
				
				JSONArray url_selezionati = new JSONArray(json.getString("url_selezionati"));
				
				
				for(int i=0; i<url_selezionati.length(); i++) {

					for(int j=0; j<video_selezionati.size(); j++) {
						if(url_selezionati.get(i).equals(video_selezionati.get(j).getUrl())) {
							video_selezionati.remove(j);
							break;
						}
					}
					
				}
		
				stati.add(new ArrayList<Video>(video_selezionati));
				posizioneStati = stati.size()-1;
				String jsonRoleAccess = new Gson().toJson(video_selezionati);
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(jsonRoleAccess.toString()); //mando il data
				out.flush();
				
			}
			else if(json.getString("azione").equals("cerca")) {
				
				String testoRicerca = json.getString("testoRicerca");
				
				ArrayList<Video> videoCercati = DBManager.getInstance().getVideoDAO().findByName(testoRicerca);
				
				JSONArray url_esclusi = new JSONArray(json.getString("url_esclusi_ricerca"));
				
				for(int i=0; i < url_esclusi.length(); i++) {
					for(int j=0; j < videoCercati.size(); j++)
					{
						if(url_esclusi.get(i).equals(videoCercati.get(j).getUrl())) {
							videoCercati.remove(j);
						}
					}
				}
	
				String jsonRoleAccess = new Gson().toJson(videoCercati);
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(jsonRoleAccess.toString()); //mando il data
				out.flush();
				
			}
			else if(json.getString("azione").equals("mostraStorico")) {
							
				ArrayList<Esito> storico = DBManager.getInstance().getStorico();
				
				String jsonRoleAccess = new Gson().toJson(storico);
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(jsonRoleAccess.toString()); //mando il data
				out.flush();
				
			}
			else if(json.getString("azione").equals("ripeti_prova")) {
				
				String id_esito = json.getString("id_esito");

				ArrayList<Video> video_esito = DBManager.getInstance().getEsitoDAO().getEsito(DBManager.getInstance().getUtenteCorrente().getEmail(),Integer.parseInt(id_esito));

				videoProva.clear();
				videoProva.addAll(video_esito);
				String jsonRoleAccess = new Gson().toJson(video_esito);
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(jsonRoleAccess.toString()); //mando il data
				out.flush();
				
			}
			else if(json.getString("azione").equals("mostra_video_esito")) {
							
				String id_esito = json.getString("id_esito");
				
				ArrayList<Video> video_esito = DBManager.getInstance().getEsitoDAO().getEsito(DBManager.getInstance().getUtenteCorrente().getEmail(),Integer.parseInt(id_esito));
				
				String jsonRoleAccess = new Gson().toJson(video_esito);
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(jsonRoleAccess.toString()); //mando il data
				out.flush();
							
			}
			else if(json.getString("azione").equals("undo")) {
				
					video_selezionati.clear();
					if(posizioneStati>0)
						posizioneStati--;

					String jsonRoleAccess = new Gson().toJson(stati.get(posizioneStati));
					
					video_selezionati.addAll(stati.get(posizioneStati));
					PrintWriter out = resp.getWriter(); //per mandare il data
					out.println(jsonRoleAccess.toString()); //mando il data
					out.flush();
			
			}
			else if(json.getString("azione").equals("redo")) {
					
					video_selezionati.clear();
					if(posizioneStati<stati.size()-1)
						posizioneStati++;

					String jsonRoleAccess = new Gson().toJson(stati.get(posizioneStati));
				
					video_selezionati.addAll(stati.get(posizioneStati));

					PrintWriter out = resp.getWriter(); //per mandare il data
					out.println(jsonRoleAccess.toString()); //mando il data
					out.flush();
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public ArrayList<Video> getVideo_selezionati() {
		return video_selezionati;
	}
	
	static public ArrayList<ArrayList<Video>> getStati() {
		return stati;
	}
	
	public int calcolaPunteggio(Video video) {
		int punteggio = 0;
		int moltiplicatore = 1;
		if(video.getRisposte().equals("false")) {
			if(video.getDifficolta().equals("FACILE"))
				moltiplicatore = 3;
			else if(video.getDifficolta().equals("NORMALE"))
				moltiplicatore = 2;
			else if(video.getDifficolta().equals("DIFFICILE"))
				moltiplicatore = 1;
		}	
		else {
			if(video.getDifficolta().equals("FACILE"))
				moltiplicatore = 1;
			else if(video.getDifficolta().equals("NORMALE"))
				moltiplicatore = 2;
			else if(video.getDifficolta().equals("DIFFICILE"))
				moltiplicatore = 3;
		}
		
		if(!video.getRisposte().getRispostaUtente()) {
			risposteErrate++;
			ArrayList<String> storicoRisposte = DBManager.getInstance().getEsitoDAO().getRisposte(DBManager.getInstance().getUtenteCorrente().getEmail(), video.getUrl(), "false");
			
			
			if(storicoRisposte.isEmpty())
				punteggio-= 5 * moltiplicatore;
			else if(storicoRisposte.size()<=10)
				punteggio-= 15 * moltiplicatore;
			else if(storicoRisposte.size()<=50)
				punteggio-= 25 * moltiplicatore;
			else
				punteggio-= 50 * moltiplicatore;
		}	
		else {
			ArrayList<String> storicoRisposte = DBManager.getInstance().getEsitoDAO().getRisposte(DBManager.getInstance().getUtenteCorrente().getEmail(), video.getUrl(), "true");
			if(storicoRisposte.isEmpty())
				punteggio+= 25;
			else if(storicoRisposte.size()<=10)
				punteggio+= 15;
			else if(storicoRisposte.size()<=50)
				punteggio+= 5;
			else
				punteggio+= 0;
		}

		return punteggio;
		
	}

}
