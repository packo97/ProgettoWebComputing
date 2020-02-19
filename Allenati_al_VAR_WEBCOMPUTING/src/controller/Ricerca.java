package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import model.Video;
import persistence.DBManager;

public class Ricerca extends HttpServlet {

		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		ArrayList<Video> risultatoRicerca = null;
		
		req.getSession().setAttribute("textRicerca", req.getParameter("textRicerca") );
		String textRicerca = req.getParameter("textRicerca");
		
		ArrayList<Video> video = DBManager.getInstance().getVideo(); 
		risultatoRicerca = new ArrayList<Video>();
		
		for(int i=0; i<video.size(); i++) {
			
			String[] titolo = video.get(i).getNome().split(" ", -1);
			for(int j=0; j<titolo.length; j++) {
				
				if(titolo[j].equalsIgnoreCase(textRicerca)) {
					risultatoRicerca.add(video.get(i));
				}
			}
			
		}
		

		req.getSession().setAttribute("risultatoRicerca", risultatoRicerca);
		
		RequestDispatcher rd = req.getRequestDispatcher("risultatoRicerca.jsp");
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
			
			if(json.getString("azione").equals("filtri")) {
				ArrayList<Video> risultatoRicerca = null;
				String categoria = json.getString("categoria");
				int durataMinima = Integer.parseInt(json.getString("durataMinima"));
				int durataMassima = Integer.parseInt(json.getString("durataMassima"));
				String periodo = json.getString("periodo");
				Calendar cal = Calendar.getInstance();
				Date dataCorrente = new Date();
				dataCorrente.setHours(0);
				dataCorrente.setMinutes(0);
				dataCorrente.setSeconds(0);
				cal.setTime(dataCorrente);
				switch(periodo) {
					case "Ultima ora":
						periodo = new Date(System.currentTimeMillis() - 3600 * 1000).toInstant().toString();
						break;
					
					case "Oggi":
						periodo = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0).toInstant().toString();
						break;
						
					case "Questa settimana":
						cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
						periodo = cal.getTime().toInstant().toString();
						break;
						
					case "Questo mese":
						cal.set(Calendar.DAY_OF_MONTH, 1);
						periodo = cal.getTime().toInstant().toString();
						break;
						
					case "Quest'anno":
						cal.set(Calendar.DAY_OF_YEAR, 1);
						periodo = cal.getTime().toInstant().toString();
						break;
						
					default:
						break;
				}
				
				String difficolta = json.getString("difficolta");
				String squadraA = json.getString("squadraA").toUpperCase();
				String squadraB = json.getString("squadraB").toUpperCase();
				String cercaIn = json.getString("cercaIn");
				String piuGiusti = json.getString("piuGiusti");
				String piuSbagliati = json.getString("piuSbagliati");
				int numeroCommentiMin = Integer.parseInt(json.getString("numeroCommentiMin"));
				int numeroCommentiMax = Integer.parseInt(json.getString("numeroCommentiMax"));
				
				risultatoRicerca = DBManager.getInstance().getVideoDAO().getRicercaPerFiltri(categoria, durataMinima, durataMassima, periodo, difficolta, squadraA, squadraB, cercaIn, piuGiusti, piuSbagliati, numeroCommentiMin, numeroCommentiMax);
			
				JSONArray url_esclusi = new JSONArray(json.getString("url_esclusi_ricerca"));
				
				for(int i=0; i < url_esclusi.length(); i++) {
					for(int j=0; j < risultatoRicerca.size(); j++)
					{
						if(url_esclusi.get(i).equals(risultatoRicerca.get(j).getUrl())) {
							risultatoRicerca.remove(j);
						}
					}
				}
				
				
				
				String jsonRoleAccess = new Gson().toJson(risultatoRicerca);
				//json.append("risultatoRicerca", risultatoRicerca);
				PrintWriter out = resp.getWriter(); //per mandare il data
				out.println(jsonRoleAccess); //mando il data
				out.flush();
				
			}						
				
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
