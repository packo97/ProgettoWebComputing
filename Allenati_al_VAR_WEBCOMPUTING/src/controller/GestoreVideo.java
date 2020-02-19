package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.Categoria;
import model.OpzioniRisposte;
import model.Video;
import persistence.DBManager;

public class GestoreVideo extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getParameter("eliminaVideo");
		String nuovoVideo=req.getParameter("aggiungiVideo");
		String urlNuovo = req.getParameter("urlNuovo");
		String nomeNuovo = req.getParameter("nomeNuovo");
		String modificaNome = req.getParameter("modificaNome");
		String videoModificato = req.getParameter("videoModificato");
		
		if(url!=null)
		{
			DBManager.getInstance().eliminaVideoPreferito(url);
			DBManager.getInstance().eliminaVideoCommento(url);
			DBManager.getInstance().eliminaVideoEsito(url);
			DBManager.getInstance().eliminaVideoCategoria(url);
			DBManager.getInstance().eliminaVideoRispostaUtente(url);
			DBManager.getInstance().eliminaVideo(url);
			
			
			req.getSession().setAttribute("eliminaVideo", url);

			RequestDispatcher rd = req.getRequestDispatcher("/html/home");
			rd.forward(req, resp);
		
			req.getSession().removeAttribute("eliminaVideo");

		}
		
		else if(nuovoVideo!=null && nuovoVideo.equals("true"))
		{	
			String link = req.getParameter("link");
			String nome = req.getParameter("nome");
			String descrizione = req.getParameter("desc");
			String difficolta = req.getParameter("diff");
			String categoria = req.getParameter("cat");
			String opzioneCorretta = req.getParameter("opC");
			String opzioneErrata = req.getParameter("opE");
			int durata = Integer.parseInt(req.getParameter("durata"));
			String squadraA = req.getParameter("squadraA").toUpperCase();
			String squadraB = req.getParameter("squadraB").toUpperCase();
			Video v = new Video(link.substring(30), link, nome, descrizione, difficolta, new Categoria(categoria), new OpzioniRisposte(opzioneCorretta, opzioneErrata,false));
			v.setSquadraA(squadraA);
			v.setSquadraB(squadraB);
			v.setDurata(durata);
			
			DBManager.getInstance().aggiungiVideo(v);

					
			req.getSession().setAttribute("link", v);
			
			RequestDispatcher rd = req.getRequestDispatcher("/html/pagina_video?url="+link);
			rd.forward(req, resp);
			
			req.getSession().removeAttribute("link");
			
			
		}
		else if(urlNuovo!=null)
		{
			if(urlNuovo.matches("https://www.youtube.com/embed/(.*)") == false)
			{
				
				resp.getOutputStream().println(
						"<div id='formatErrato' class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Formato video non valido!</strong></div>"
						);
			}
			else
			{
				if(DBManager.getInstance().getVideoDAO().esisteVideo(urlNuovo))
				{
					resp.getOutputStream().println(
							"<div id='formatErrato' class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Il video &egrave; gi&agrave; presente!</strong></div>"
							);
				}
				else
				{
					resp.getOutputStream().println("urlCorretto");
				}
			}
		}
		else if(nomeNuovo!=null && nomeNuovo!="")
		{
			if(DBManager.getInstance().getVideoDAO().esisteNome(nomeNuovo))
			{
				resp.getOutputStream().println(
						"<div id='formatErrato' class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Nome gi&agrave; utilizzato!</strong></div>"
						);
			}
			else
			{
				resp.getOutputStream().println("tuttoAPosto");	
			}

		}
		else if(modificaNome!=null && modificaNome!="")
		{
			if(DBManager.getInstance().getVideoDAO().esisteNomeModifica(modificaNome,req.getParameter("link")))
			{
				resp.getOutputStream().println(
						"<div id='formatErrato' class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Nome gi&agrave; utilizzato!</strong></div>"
						);
			}
			else
			{
				resp.getOutputStream().println("tuttoAPosto");	
			}
		}
		else if(videoModificato!=null && videoModificato.equals("true"))
		{	
			String link = req.getParameter("link");
			String nome = req.getParameter("nome");
			String descrizione = req.getParameter("desc");
			String difficolta = req.getParameter("diff");
			String categoria = req.getParameter("cat");
			String opzioneCorretta = req.getParameter("opC");
			String opzioneErrata = req.getParameter("opE");
			int durata = Integer.parseInt(req.getParameter("durata"));
			String squadraA = req.getParameter("squadraA").toUpperCase();
			String squadraB = req.getParameter("squadraB").toUpperCase();
			
			Video v = new Video(link.substring(30), link, nome, descrizione, difficolta, new Categoria(categoria), new OpzioniRisposte(opzioneCorretta, opzioneErrata,false));
			
			v.setSquadraA(squadraA);
			v.setSquadraB(squadraB);
			v.setDurata(durata);
			
			DBManager.getInstance().modificaVideo(v);
			req.getSession().setAttribute("modificato", v);
			
			RequestDispatcher rd = req.getRequestDispatcher("/html/pagina_video?url="+link);
			rd.forward(req, resp);
			
			req.getSession().removeAttribute("modificato");
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//doGet(req, resp);
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));		
		String line = reader.readLine();
		while (line != null){
			jsonReceived.append(line);
			line = reader.readLine();
		}		
		
		try {
			JSONObject json = new JSONObject(jsonReceived.toString());				
			
			if(json.getString("azione").equals("rimuovi_preferiti")) {				
				DBManager.getInstance().getPreferitiDAO().deleteUrlPreferiti(json.getString("url"));
			}
			
			PrintWriter out = resp.getWriter(); //per mandare il data
			out.println(json.toString()); //mando il data
			out.flush();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
