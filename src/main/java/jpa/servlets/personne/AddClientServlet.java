package jpa.servlets.personne;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.objects.Client;
import jpa.objects.Location;

@WebServlet(name="Addclient",
urlPatterns={"/addClient"})
public class AddClientServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		resp.sendRedirect("client/addClient.html");

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Client c = new Client();
		c.setFirstName(request.getParameter("fname"));
		c.setLastName(request.getParameter("lname"));
		c.setEmail(request.getParameter("email"));
		c.setPassword(request.getParameter("password"));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();
		manager.persist(c);
		tx.commit();
		
		manager.close();
		factory.close();
		
		out.println("<HTML>\n<BODY>\n" +
					"<H1>Recapitulatif des informations</H1>\n" +
					"<UL>\n" +			
						" <LI>Nom: "
						+ request.getParameter("fname") + "\n" +
						" <LI>Prenom: "
						+ request.getParameter("lname") + "\n" +
						" <LI>Age: "
						+ request.getParameter("email") + "\n" +
					"</UL>\n" +	
					"<a href=\"/\">Retourner à l'accueil</a>"+
			"</BODY></HTML>");
	}	
	
}
