package controller;


import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DBManager;

public class GestoreEsito extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("esito.jsp");
		String data = req.getParameter("data");
		
		req.getSession().setAttribute("esito", DBManager.getInstance().getEsito(Integer.parseInt(req.getParameter("id_esito"))));
		
		rd.forward(req, resp);
	}
}
