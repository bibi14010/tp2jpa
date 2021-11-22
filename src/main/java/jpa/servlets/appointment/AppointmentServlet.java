package jpa.servlets.appointment;

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

import jpa.dao.AppointmentDao;
import jpa.objects.Appointment;


@WebServlet(name="AppointmentServlet", urlPatterns="/appointment")
public class AppointmentServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException, IOException {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		AppointmentDao appointmentDao = new AppointmentDao(manager);
		List<Appointment> appointments = appointmentDao.find();
		
		PrintWriter p = new PrintWriter(resp.getOutputStream());
		p.print("<!DOCTYPE html><html>");
		if(appointments.size()>0) {
			for (Appointment appointment : appointments) {
				p.print(appointment.getDate()+"-"+appointment.getPrestataire().getEmail()+"-"+appointment.getClient().getEmail()+"-"+appointment.getLocation().toString());
				p.print("\n");
			}			
		}else {
			p.print("No appointment found.<br>");
		}
		p.println("<a href=\"/\">Retourner à l'accueil</a></html>");
		p.flush();
		manager.close();
		factory.close();
	}

}
