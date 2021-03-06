package jpa.servlets.location;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.EntityManagerHelper;
import jpa.dao.LocationDao;
import jpa.objects.Location;

@WebServlet(name="location",
urlPatterns={"/location"})
public class LocationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		LocationDao locationDao = new LocationDao(manager);
		List<Location> location = locationDao.find();
		
		PrintWriter p = new PrintWriter(resp.getOutputStream());
		p.print("<!DOCTYPE html><html>");
		if(location.size()>0) {
			for (Location location2 : location) {
				p.print(location2.toString());			
				p.print("\n");
			}			
		}else {
			p.print("No location found.<br>");
		}
		p.println("<a href=\"/\">Retourner ? l'accueil</a></html>");
		p.flush();
		manager.close();
		factory.close();
	}

}
