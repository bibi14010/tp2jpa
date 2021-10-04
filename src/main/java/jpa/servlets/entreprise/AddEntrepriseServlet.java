package jpa.servlets.entreprise;

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

import jpa.dao.LocationDao;
import jpa.objects.Entreprise;
import jpa.objects.Location;

@WebServlet(name="Addentreprise",
urlPatterns={"/addEntreprise"})
public class AddEntrepriseServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"UTF-8\">\r\n"
				+ "		<title>Ajouter un nouveau lieu</title>\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<FORM Method=\"POST\" Action=\"/addEntreprise\">"
				+ "		Nom de l'entreprise :<INPUT type=\"text\" required=\"true\" size=\"20\" name=\"name\"><BR>");
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		LocationDao locationDao = new LocationDao(manager);
		List<Location> location = locationDao.find();
		out.print("<select name=\"location\">");
		for (Location location2 : location) {
			out.print("<option value="+location2.getId()+">"+location2.getCity()+","+location2.getStreetNumber()+" "+location2.getStreet()+","+location2.getPostCode()+"</option>");
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

		Entreprise e = new Entreprise();
		e.setName(request.getParameter("name"));
		LocationDao locationDao = new LocationDao(manager);
		List<Location> l = locationDao.findById(Integer.valueOf(request.getParameter("location")));
		e.setLocation(l.get(0));
		
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();
		manager.persist(e);
		tx.commit();
		
		manager.close();
		factory.close();
		
		out.println("<HTML>\n<BODY>\n" +
					"<H1>Recapitulatif des informations</H1>\n" +
					"<UL>\n" +			
			" <LI>Nom: "
					+ request.getParameter("name") + "\n" +
					" <LI>Lieu: "
					+ l.get(0).getCity() + "\n" +
					" <LI>- "
					+ l.get(0).getStreetNumber() + "\n" +
					" <LI>-: "
					+ l.get(0).getStreet() + "\n" +
					" <LI>-: "
					+ l.get(0).getPostCode() + "\n" +
					"</UL>\n" +				
			"</BODY></HTML>");
	}	
}
