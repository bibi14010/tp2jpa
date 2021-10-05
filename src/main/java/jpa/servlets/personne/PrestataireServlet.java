package jpa.servlets.personne;

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

import jpa.dao.PrestataireDao;
import jpa.objects.Prestataire;

@WebServlet(name="PrestataireServlet", urlPatterns="/prestataire")
public class PrestataireServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException, IOException {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		PrestataireDao prestataireDao = new PrestataireDao(manager);
		List<Prestataire> prestataires = prestataireDao.find();
		
		PrintWriter p = new PrintWriter(resp.getOutputStream());
		if(prestataires.size()>0) {
			for (Prestataire prestataire : prestataires) {
				p.print(prestataire.getFirstName()+" "+prestataire.getLastName()+"-"+prestataire.getEmail()+"-"+prestataire.getEntreprise().getName());
				p.print("\n");
			}			
		}else {
			p.print("No prestataire found.");
		}
		p.println("<a href=\"/\">Retourner à l'accueil</a>");
		p.flush();
		manager.close();
		factory.close();
	}
}
