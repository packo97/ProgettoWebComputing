package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.AddPortOffsetRule;
import org.json.JSONException;
import org.json.JSONObject;

import model.Video;
import persistence.DBManager;

public class MostraVideo extends HttpServlet{

	
	Video videoChiesto = null;
	String url = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		if(req.getParameter("url") != null)
			url = req.getParameter("url");
		
		if(url!=null) {
			req.getSession().setAttribute("url", req.getParameter("url"));
			for (Video video : DBManager.getInstance().getVideo()) {
				if(video.getUrl().equals(req.getParameter("url"))){
					req.getSession().setAttribute("id", video.getId());
					
				}
				
			}
			ArrayList<Video> video = DBManager.getInstance().getVideo();

			for(Video v : video) {
				if(v.getUrl().equals(url)) {
					videoChiesto = v;
				}
					
			}

			DBManager.getInstance().getUtenteCorrente().aggiornaRecenti(videoChiesto);
			
			//videoChiesto.setVisualizzazioni(videoChiesto.getVisualizzazioni()+1); 
			req.getSession().setAttribute("nome", videoChiesto.getNome());
			req.getSession().setAttribute("descrizione", videoChiesto.getDescrizione());
			req.getSession().setAttribute("categoria", videoChiesto.getCategoria().get(0).getNome()); //da correggere
			req.getSession().setAttribute("difficolta", videoChiesto.getDifficolta());
			req.getSession().setAttribute("visualizzazioni", videoChiesto.getVisualizzazioni());
			req.getSession().setAttribute("ordineRisposte", new Random().nextInt(2));
			
			req.getSession().setAttribute("rispostaCorretta", videoChiesto.getRisposte().getOpzioneCorretta());
			req.getSession().setAttribute("rispostaErrata", videoChiesto.getRisposte().getOpzioneErrata());

			req.getSession().setAttribute("isPreferito", DBManager.getInstance().isPreferito(videoChiesto));
			req.getSession().setAttribute("lista_commenti", DBManager.getInstance().getCommenti(videoChiesto.getUrl()).getLista_commenti());
			
		}

		RequestDispatcher rd = req.getRequestDispatcher("pagina_video.jsp");
			
		req.getSession().setAttribute("amministratore", DBManager.getInstance().getUtenteCorrente().getAmministratore());
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
			
			if(json.getString("azione").equals("preferiti")) {
				
				
				
				DBManager.getInstance().aggiungiAiPreferiti(videoChiesto);
				req.getSession().setAttribute("isPreferito", DBManager.getInstance().isPreferito(videoChiesto));
				
				json.append("isPreferito",DBManager.getInstance().isPreferito(videoChiesto));
				
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(json.toString()); //mando il data
				out.flush();
				
			
				
			}else if(json.getString("azione").equals("commento")) {
				
				JSONObject jsonCompleto = new JSONObject();
				String testo = new String(json.getString("testo").getBytes(), "UTF-8");
				jsonCompleto.append("testo", testo);
				jsonCompleto.append("url", json.get("url_video"));
				jsonCompleto.append("nome", DBManager.getInstance().getUtenteCorrente().getNome());
				jsonCompleto.append("cognome", DBManager.getInstance().getUtenteCorrente().getCognome());
				jsonCompleto.append("data", new Date().toLocaleString());
			
				DBManager.getInstance().aggiungiCommento(json.getString("testo"),json.getString("url_video"));
				
				//PrintWriter out = resp.getWriter(); //per mandare il data
				resp.getOutputStream().write(jsonCompleto.toString().getBytes("UTF-8")); //mando il data
				//out.flush();				
			}
			else if(json.getString("azione").equals("risposta")) {
				DBManager.getInstance().getRispostaUtenteDAO().save(json.getString("url_video"), Boolean.parseBoolean(json.getString("valore")));
			}
	
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}