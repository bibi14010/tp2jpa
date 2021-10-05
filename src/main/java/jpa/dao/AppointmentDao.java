package jpa.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import jpa.objects.Appointment;
import jpa.objects.Client;
import jpa.objects.Location;
import jpa.objects.Prestataire;

public class AppointmentDao {

	private EntityManager manager;

	public AppointmentDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Appointment> find() {
		List<Appointment> resultList = manager.createQuery("select a from Appointment a",Appointment.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Appointment> findByDate(Date date) {
		List<Appointment> resultList = manager.createQuery("select a from Appointment a where a.date="+date,Appointment.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Appointment> findByLocation(Location l) {
		List<Appointment> resultList = manager.createQuery("select a from Appointment a where a.location.id="+l.getId(),Appointment.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Appointment> findByPrestataire(Prestataire p) {
		List<Appointment> resultList = manager.createQuery("select a from Appointment a where a.prestataire.id="+p.getId(),Appointment.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Appointment> findByClient(Client c) {
		List<Appointment> resultList = manager.createQuery("select a from Appointment a where a.client.id="+c.getId(),Appointment.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	
	private void displayList(List<Appointment> resultList) {
		System.out.println("Appointments Avaliable : "+resultList.size());
		for (Appointment appointment : resultList) {
			System.out.println("- Id of Appointment is "+appointment.getId());
		}
	}
}
