package jpa.servlets.appointment;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import jpa.dao.ClientDao;
import jpa.dao.LocationDao;
import jpa.dao.PrestataireDao;
import jpa.objects.Appointment;
import jpa.objects.Client;
import jpa.objects.Location;
import jpa.objects.Prestataire;

@WebServlet(name="addAppointment", urlPatterns="/addAppointment")
public class addAppointment extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"UTF-8\">\r\n"
				+ "		<title>Créer un rendez-vous</title>\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<FORM Method=\"POST\" Action=\"/addAppointment\">");
		
		out.print("\n<label>Date du rendez-vous</label> <input type=\"date\" name=\"date\" required>");
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		ClientDao clientDao = new ClientDao(manager);
		List<Client> clients = clientDao.find();
		out.print("\n<label>Choix du client</label><select name=\"client\" required>");
		for (Client client : clients) {
			out.print("<option value="+client.getId()+">"+client.getFirstName()+" "+client.getLastName()+"</option>");
		}
		out.println("</select>");
		
		PrestataireDao prestataireDao = new PrestataireDao(manager);
		List<Prestataire> prestataires = prestataireDao.find();
		out.print("\\n<label>Choix du prestataire</label><select name=\"prestataire\" required>");
		for (Prestataire prestataire : prestataires) {
			out.print("<option value="+prestataire.getId()+">"+prestataire.getFirstName()+" "+prestataire.getLastName()+"</option>");
		}
		out.println("</select>");
		
		LocationDao locationDao = new LocationDao(manager);
		List<Location> locations = locationDao.find();
		out.print("<label>Choix du lieu</label><select name=\"location\" required>");
		for (Location location : locations) {
			out.print("<option value="+location.getId()+">"+location.getCity()+","+location.getStreetNumber()+" "+location.getStreet()+","+location.getPostCode()+"</option>");
		}
		out.println("</select>");
		
		out.print("\n<label>Description</label> <input type=\"textarea\" name=\"descritpion\">");
		
		out.print("\n<label>Durée (en minutes)</label> <input type=\"number\" name=\"time\">");
		
		out.println("<INPUT type=submit value=Send>\r\n"
				+ "		</FORM>\r\n"
				+"<a href=\"/\">Retourner à l'accueil</a>"
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
		
		Appointment a = new Appointment();
		
		ClientDao clientDao = new ClientDao(manager);
		List<Client> clients = clientDao.findById(Integer.valueOf(request.getParameter("client")));
		a.setClient(clients.get(0));
		
		PrestataireDao prestataireDao = new PrestataireDao(manager);
		List<Prestataire> prestataires = prestataireDao.findById(Integer.valueOf(request.getParameter("prestataire")));
		a.setPrestataire(prestataires.get(0));
		
		LocationDao locationDao = new LocationDao(manager);
		List<Location> locations = locationDao.findById(Integer.valueOf(request.getParameter("location")));
		a.setLocation(locations.get(0));
		
		DateTimeFormatter f  = DateTimeFormatter.ofPattern( "uuu-MM-dd" ) ;
		a.setDate(LocalDate.parse(request.getParameter("date"),f));
		
		a.setDescription(request.getParameter("descriptio,"));
		
		a.setTime(Integer.valueOf(request.getParameter("time")));
		
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();

		manager.persist(a);
		tx.commit();
		
		manager.close();
		factory.close();
		
		out.println("<HTML>\n<BODY>\n" +
				"<H1>Recapitulatif des informations</H1>\n" +
				"<UL>\n" +			
				" <LI>Client: "
					+ clients.get(0).getFirstName()+" "+clients.get(0).getLastName() + "\n" +
					" <LI>Prestataire: "
					+ prestataires.get(0).getFirstName()+" "+prestataires.get(0).getLastName() + "\n" +
					" <LI>Date: "
					+ request.getParameter("date") + "\n" +
					"<LI> Lieu: "
					+locations.get(0).getCity()+","+locations.get(0).getStreetNumber()+" "+locations.get(0).getStreet()+","+locations.get(0).getPostCode()+
				"</UL>\n" +	
				"<a href=\"/\">Retourner à l'accueil</a>"+
		"</BODY></HTML>");
	}
}
