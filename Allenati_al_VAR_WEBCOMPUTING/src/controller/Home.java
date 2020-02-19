package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Video;
import persistence.DBManager;

public class Home extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			ArrayList<String> sezioni = new ArrayList<String>();
			sezioni.add("piu_visti");
			sezioni.add("recenti");
			sezioni.add("video");
			req.getSession().setAttribute("sezioni", sezioni);
			
			List<Video> video = DBManager.getInstance().getVideo();
			req.getSession().setAttribute("video", video);
			List<Video> piu_visti = DBManager.getInstance().getPiuVisti();
			req.getSession().setAttribute("video_piu_visti",piu_visti);
			List<Video> recenti = DBManager.getInstance().getUtenteCorrente().getRecenti();
			req.getSession().setAttribute("video_recenti", recenti);
			RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
			rd.forward(req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
}
