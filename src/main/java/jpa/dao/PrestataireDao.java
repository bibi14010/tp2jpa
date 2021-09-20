package jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;

import jpa.objects.Appointment;
import jpa.objects.Entreprise;
import jpa.objects.Prestataire;

public class PrestataireDao {
	private EntityManager manager;

	public PrestataireDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void find() {
		List<Prestataire> resultList = manager.createQuery("select p from Prestataire p",Prestataire.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findById(int id) {
		List<Prestataire> resultList = manager.createQuery("select p from Prestataire p where p.id="+id,Prestataire.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByEntreprise(Entreprise e) {
		List<Prestataire> resultList = manager.createQuery("select p from Prestataire p where p.entreprise.id="+e.getId(),Prestataire.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByAppointment(Appointment a) {
		List<Prestataire> resultList = manager.createQuery("select a.prestataire from Appointment a where a.id="+a.getId(),Prestataire.class).getResultList();
		this.displayList(resultList);
	}
	
	private void displayList(List<Prestataire> resultList) {
		System.out.println("Prestataires Avaliable : "+resultList.size());
		for (Prestataire entreprise : resultList) {
			System.out.println("- Id of Prestataire is "+entreprise.getId());
		}
	}
}
