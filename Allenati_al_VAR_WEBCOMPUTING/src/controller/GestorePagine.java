package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import model.Esito;
import model.Utente;
import model.Video;
import persistence.DBManager;

public class GestorePagine extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String pagina = req.getParameter("pagina");
		if(pagina!=null)
		{
			if(pagina.equals("preferiti")) {
				ArrayList<Video> preferiti = DBManager.getInstance().getPreferiti();
				req.getSession().setAttribute("video_preferiti",preferiti);
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.contentEquals("storico")) {
				req.getSession().setAttribute("storico",DBManager.getInstance().getStorico());
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.contentEquals("registrati")){
				//operzioni per la pagina di registrazione
				req.getSession().removeAttribute("nome");
				req.getSession().removeAttribute("cognome");
				req.getSession().removeAttribute("email");
				req.getSession().removeAttribute("password");
				req.getSession().removeAttribute("confPassword");
				req.getSession().removeAttribute("amministratoreSi");
				
				
				req.getSession().removeAttribute("passwordSbagliata");
				req.getSession().removeAttribute("emailSbagliato");
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
				return;
			}
			else if(pagina.equals("modificaVideo"))
			{
				req.getSession().setAttribute("url", req.getParameter("url"));
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.equals("editor_prova_autovalutazione")) {
				GestoreProvaAutovalutazione.getVideo_selezionati().clear();
				GestoreProvaAutovalutazione.getStati().clear();
				GestoreProvaAutovalutazione.getStati().add(new ArrayList<Video>());
				req.getSession().setAttribute("video", DBManager.getInstance().getVideoDAO().findAll());
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.equals("classifica")){
				
				ArrayList<Utente> utenti = DBManager.getInstance().getUtenteDAO().findAll();

				req.getSession().setAttribute("utenti", utenti);
				
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.equals("statistiche_utente")) {
				
				req.getSession().setAttribute("utente", DBManager.getInstance().getUtenteDAO().findByPrimaryKey(DBManager.getInstance().getUtenteCorrente().getEmail(), DBManager.getInstance().getUtenteCorrente().getPassword()));
				
				if(req.getParameter("utente")!=null) {
					
					
					req.getSession().setAttribute("utente", DBManager.getInstance().getUtenteDAO().getUtente(req.getParameter("utente")));
				}
				
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
		}
		
		
		
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
		
		
		
		ArrayList<Integer> punteggiCategorie = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> punteggiCategoriePerMese = new ArrayList<ArrayList<Integer>>();
		JSONObject jsonStatistiche = new JSONObject();
		
		ArrayList<Video> classificaVideo = new ArrayList<Video>();
		ArrayList<Integer> risposteCorretteVideo = new ArrayList<Integer>();
		try {
			JSONObject json = new JSONObject(jsonReceived.toString());				
			
			if(json.getString("azione").equals("getStatisticheCategorie")) {
				
				punteggiCategorie.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoria(json.getString("utente"), "SPA"));
				punteggiCategorie.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoria(json.getString("utente"), "DOGSO"));
				punteggiCategorie.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoria(json.getString("utente"), "RIGORE"));
				punteggiCategorie.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoria(json.getString("utente"), "FALLO DI MANO"));
				punteggiCategorie.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoria(json.getString("utente"), "AMMONIZIONE"));
				punteggiCategorie.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoria(json.getString("utente"), "ESPULSIONE"));
				
				for(int i=0; i<punteggiCategorie.size(); i++) {
					if(punteggiCategorie.get(i)<0)
						punteggiCategorie.set(i, 0);
				}
				
				punteggiCategoriePerMese.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoriaAndMese(json.getString("utente"), "SPA"));
				punteggiCategoriePerMese.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoriaAndMese(json.getString("utente"), "DOGSO"));
				punteggiCategoriePerMese.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoriaAndMese(json.getString("utente"), "RIGORE"));
				punteggiCategoriePerMese.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoriaAndMese(json.getString("utente"), "FALLO DI MANO"));
				punteggiCategoriePerMese.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoriaAndMese(json.getString("utente"), "AMMONIZIONE"));
				punteggiCategoriePerMese.add(DBManager.getInstance().getVideoDAO().getPunteggioForCategoriaAndMese(json.getString("utente"), "ESPULSIONE"));
				
				
				int numeroProvePositive = DBManager.getInstance().getEsitoDAO().getNumeroProvePerRisultato(json.getString("utente"), "true");
				int numeroProveNegative = DBManager.getInstance().getEsitoDAO().getNumeroProvePerRisultato(json.getString("utente"), "false");
				
				
				jsonStatistiche.put("punteggiCategorie", new JSONArray(punteggiCategorie));
				jsonStatistiche.put("punteggiCategoriePerMese", new JSONArray(punteggiCategoriePerMese));
				jsonStatistiche.put("numeroProvePositive", numeroProvePositive);
				jsonStatistiche.put("numeroProveNegative", numeroProveNegative);
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(jsonStatistiche.toString()); //mando il data
				out.flush();
			}
			else if(json.getString("azione").equals("classifica")) {
				
				for(int i = 0; i<DBManager.getInstance().getVideoDAO().getClassifica(json.getString("filtro")).size(); i++) {
					classificaVideo.add(DBManager.getInstance().getVideoDAO().getClassifica(json.getString("filtro")).get(i).fst);
					risposteCorretteVideo.add(DBManager.getInstance().getVideoDAO().getClassifica(json.getString("filtro")).get(i).snd);
				}
				
				JSONObject jsonClassifica = new JSONObject();
				jsonClassifica.put("video", new JSONArray(new Gson().toJson(classificaVideo)));
				jsonClassifica.put("risposteCorrette", risposteCorretteVideo);
			
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(jsonClassifica.toString()); //mando il data
				out.flush();
			}
			else if(json.getString("azione").equals("classifica_sbagliati")) {
				
			}
			
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}