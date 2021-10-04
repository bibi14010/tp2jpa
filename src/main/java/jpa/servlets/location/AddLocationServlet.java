package jpa.servlets.location;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.objects.Location;

@WebServlet(name="Addlocation",
urlPatterns={"/addLocation"})
public class AddLocationServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		resp.sendRedirect("location/addLocation.html");

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Location l = new Location();
		l.setCity(request.getParameter("city"));
		l.setStreet(request.getParameter("street"));
		l.setStreetNumber(Integer.valueOf(request.getParameter("streetNumber")));
		l.setPostCode(Integer.valueOf(request.getParameter("postCode")));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();
		manager.persist(l);
		tx.commit();
		
		manager.close();
		factory.close();
		
		out.println("<HTML>\n<BODY>\n" +
					"<H1>Recapitulatif des informations</H1>\n" +
					"<UL>\n" +			
			" <LI>Nom: "
					+ request.getParameter("city") + "\n" +
					" <LI>Prenom: "
					+ request.getParameter("street") + "\n" +
					" <LI>Age: "
					+ request.getParameter("streetNumber") + "\n" +
					" <LI>Age: "
					+ request.getParameter("postCode") + "\n" +
					"</UL>\n" +				
			"</BODY></HTML>");
	}	
	
}
