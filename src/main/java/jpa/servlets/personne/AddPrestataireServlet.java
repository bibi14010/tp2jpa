package jpa.servlets.personne;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.dao.EntrepriseDao;
import jpa.objects.Entreprise;
import jpa.objects.Prestataire;

@WebServlet(name="addPrestataire", urlPatterns="/addPrestataire")
public class AddPrestataireServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"UTF-8\">\r\n"
				+ "		<title>Ajouter un nouveau prestataire</title>\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<FORM Method=\"POST\" Action=\"/addPrestataire\">");
		
		out.print("			<label>First Name:	</label> 	<INPUT type=\"text\" required name=\"fname\"><BR>\r\n"
				+ "			<label>Last Name :	</label> 	<INPUT type=\"text\" required name=\"lname\"><BR>\r\n"
				+ "			<label>Email :		</label>	<INPUT type=\"email\" required name=\"email\"><BR>\r\n"
				+ "			<label>Password :	</label> 	<INPUT type=\"password\" required  name=\"password\"><BR>");
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		EntrepriseDao entrepriseDao = new EntrepriseDao(manager);
		List<Entreprise> entreprise = entrepriseDao.find();
		out.print("<select name=\"entreprise\">");
		for (Entreprise entreprise2 : entreprise) {
			out.print("<option value="+entreprise2.getId()+">"+entreprise2.getName()+"</option>");
		}
		out.print("</select>");
		
		out.println("<INPUT type=submit value=Send>\r\n"
				+ "		</FORM>\r\n"
				+ "	</body>\r\n"
				+ "</html>");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Prestataire p = new Prestataire();
		p.setFirstName(request.getParameter("fname"));
		p.setLastName(request.getParameter("lname"));
		p.setEmail(request.getParameter("email"));
		p.setPassword(request.getParameter("password"));
		
		EntrepriseDao entrepriseDao = new EntrepriseDao(manager);
		List<Entreprise> e = entrepriseDao.findById(Integer.valueOf(request.getParameter("entreprise")));
		p.setEntreprise(e.get(0));
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();

		manager.persist(p);
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
		"</BODY></HTML>");
	}
}
