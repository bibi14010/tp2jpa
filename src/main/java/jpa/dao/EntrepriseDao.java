package jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;

import jpa.objects.Appointment;
import jpa.objects.Entreprise;
import jpa.objects.Location;
import jpa.objects.Prestataire;

public class EntrepriseDao {

	private EntityManager manager;

	public EntrepriseDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void find() {
		List<Entreprise> resultList = manager.createQuery("select e from Entreprise e",Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findById(int id) {
		List<Entreprise> resultList = manager.createQuery("select e from Entreprise e where id="+id,Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByLocation(Location l) {
		List<Entreprise> resultList = manager.createQuery("select e from Entreprise e where e.location.id="+l.getId(),Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByName(String name) {
		List<Entreprise> resultList = manager.createQuery("select e from Entreprise e where e.name like %"+name+"%",Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByAppointemnt(Appointment a) {
		List<Entreprise> resultList = manager.createQuery("select e from Entreprise e where e.id="+a.getPrestataire().getEntreprise().getId(),Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByCity(String city) {
		List<Entreprise> resultList = manager.createQuery("select e from Entreprise e where e.location.city like %"+city+"%",Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByStreet(String street) {
		List<Entreprise> resultList = manager.createQuery("select e from Entreprise e where e.location.street like %"+street+"%",Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByPostCode(int pc) {
		List<Entreprise> resultList = manager.createQuery("select e from Entreprise e where e.location.postCode="+pc,Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByPrestataire(Prestataire p) {
		List<Entreprise> resultList = manager.createQuery("select p.entreprise from Prestataire p where p.id="+p.getId(),Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findBySector(String sector) {
		List<Entreprise> resultList = manager.createQuery("select e from Entreprise e where e.sector like %"+sector+"%",Entreprise.class).getResultList();
		this.displayList(resultList);
	}
	
	private void displayList(List<Entreprise> resultList) {
		System.out.println("Entreprises Avaliable : "+resultList.size());
		for (Entreprise entreprise : resultList) {
			System.out.println("- Id of Entreprise is "+entreprise.getId());
		}
	}
}
