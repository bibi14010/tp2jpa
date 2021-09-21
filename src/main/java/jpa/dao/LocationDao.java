package jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpa.objects.Appointment;
import jpa.objects.Entreprise;
import jpa.objects.Location;

public class LocationDao {
	
	private EntityManager manager;

	public LocationDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Location> find() {
		List<Location> resultList = manager.createNamedQuery("toutesLesLocations",Location.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Location> findByCity(String city) {
		Query query = manager.createNamedQuery("toutesLesLocationsParVille",Location.class);
		query.setParameter("cityWanted",city);
		List<Location> resultList = query.getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Location> findByEntreprise(Entreprise e) {
		List<Location> resultList = manager.createQuery("select e.location from Entreprise e where e.location.id="+e.getLocation().getId(),Location.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Location> findByAppointment(Appointment a) {
		List<Location> resultList = manager.createQuery("select l from Location l where l.id="+a.getLocation().getId(),Location.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	private void displayList(List<Location> resultList) {
		System.out.println("Locations Avaliable : "+resultList.size());
		for (Location location : resultList) {
			System.out.println("- Id of Location is "+location.getId());
		}
	}
}
