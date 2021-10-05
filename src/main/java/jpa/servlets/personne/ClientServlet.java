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

import jpa.dao.ClientDao;
import jpa.objects.Client;

@WebServlet(name="ClientServlet", urlPatterns="/client")
public class ClientServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException, IOException {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		ClientDao clientDao = new ClientDao(manager);
		List<Client> clients = clientDao.find();
		
		PrintWriter p = new PrintWriter(resp.getOutputStream());
		if(clients.size()>0) {
			for (Client client : clients) {
				p.print(client.getFirstName()+" "+client.getLastName()+"-"+client.getEmail());
				p.print("\n");
			}			
		}else {
			p.print("No client found.");
		}
		p.flush();
		manager.close();
		factory.close();
	}
		
}
	

