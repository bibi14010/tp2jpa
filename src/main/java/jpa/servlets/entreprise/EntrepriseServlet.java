package jpa.servlets.entreprise;

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

import jpa.dao.EntrepriseDao;
import jpa.objects.Entreprise;

@WebServlet(name="Entreprise",
urlPatterns={"/entreprise"})
public class EntrepriseServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		EntrepriseDao entrepriseDao = new EntrepriseDao(manager);
		List<Entreprise> entreprises = entrepriseDao.find();
		
		PrintWriter p = new PrintWriter(resp.getOutputStream());
		if(entreprises.size()>0) {
			for (Entreprise entreprise : entreprises) {
				p.print(entreprise.getName()+"-"+entreprise.getLocation().getCity());		
				p.print("\n");
			}			
		}else {
			p.print("No entreprise found");
		}
		p.println("<a href=\"/\">Retourner à l'accueil</a>");
		p.flush();
		manager.close();
		factory.close();
	}
}
