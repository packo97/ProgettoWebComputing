package controller;



import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.Video;
import persistence.DBManager;


public class Login extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			RequestDispatcher rd = req.getRequestDispatcher("/index");
			rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		if (req.getParameter("login")!= null && req.getParameter("login").equals("true")) {

			String email = req.getParameter("email");
			String password = req.getParameter("password");
			Utente utente = DBManager.getInstance().login(email,password);

			if (utente != null) {
				req.getSession().removeAttribute("loginSbagliato");
				DBManager.getInstance().setUtenteCorrente(utente);
				req.getSession().setAttribute("utente", utente);
						
				DBManager.getInstance().setUtenteCorrente(utente);
				
				req.getSession().setAttribute("amministratore", DBManager.getInstance().getUtenteCorrente().getAmministratore());
				req.getSession().setAttribute("datiUtente", "Benvenuto/a " + utente.getNome() + " " + utente.getCognome());
				RequestDispatcher rd = req.getRequestDispatcher("/html/home");
				rd.forward(req, resp);
			}
			else {
				req.getSession().setAttribute("loginSbagliato", "si");
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, resp);
			}
		}
		else if (req.getParameter("registrazione")!= null && req.getParameter("registrazione").equals("true")){
			req.getSession().removeAttribute("loginSbagliato");

			String nome = req.getParameter("nome");
			String cognome = req.getParameter("cognome");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String confPassword = req.getParameter("conferma_password");
			String amministratore = req.getParameter("amministratore");
			if(amministratore==null)
				amministratore="off";
			
			boolean esisteEmail=DBManager.getInstance().esisteEmail(email);
						
			if(password.equals(confPassword) && !esisteEmail) 
			{
				req.getSession().setAttribute("registrazioneEffettuata", "corretto");
				Utente user = new Utente(nome,cognome,email,password,amministratore);
				DBManager.getInstance().inserisciUtente(user);
				
				req.setAttribute("utenteRegistrato", user);
				
				req.getSession().removeAttribute("passwordSbagliata");
				req.getSession().removeAttribute("emailSbagliata");

				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, resp);
				req.getSession().removeAttribute("registrazioneEffettuata");
				
			}
			else if(!password.equals(confPassword) || esisteEmail)
			{
				req.getSession().setAttribute("nome", nome);
				req.getSession().setAttribute("cognome", cognome);
				req.getSession().setAttribute("email", email);
				req.getSession().setAttribute("password", password);
				req.getSession().setAttribute("confPassword", confPassword);
				req.getSession().setAttribute("amministratoreSi", amministratore);

				if(!password.equals(confPassword) && !esisteEmail)
				{
					req.getSession().removeAttribute("emailSbagliata");
					req.getSession().setAttribute("passwordSbagliata", "si");
				}
				if(esisteEmail && password.contentEquals(confPassword))
				{
					req.getSession().removeAttribute("passwordSbagliata");
					req.getSession().setAttribute("emailSbagliato", "si");
				}
				if(!password.equals(confPassword) && esisteEmail)
				{
					req.getSession().setAttribute("passwordSbagliata", "si");
					req.getSession().setAttribute("emailSbagliato", "si");
				}
					RequestDispatcher rd = req.getRequestDispatcher("registrati.jsp");
					rd.forward(req, resp);
			}

			
			
			req.getSession().removeAttribute("emailSbagliato");

			
		}
	}
}