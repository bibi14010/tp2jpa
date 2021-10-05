package jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;

import jpa.objects.Appointment;
import jpa.objects.Client;

public class ClientDao {

	private EntityManager manager;

	public ClientDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Client> find() {
		List<Client> resultList = manager.createQuery("select c from Client c",Client.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Client> findById(int id) {
		List<Client> resultList = manager.createQuery("select c from Client c where c.id="+id,Client.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Client> findByEmail(String email) {
		List<Client> resultList = manager.createQuery("select c from Client c where c.email="+email,Client.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Client> findByFirstName(String firstName) {
		List<Client> resultList = manager.createQuery("select c from Client c where c.firstName="+firstName,Client.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Client> findByLastName(String lastName) {
		List<Client> resultList = manager.createQuery("select c from Client c where c.lastName="+lastName,Client.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Client> findByFullName(String firstName,String lastName) {
		List<Client> resultList = manager.createQuery("select c from Client c where c.lastName="+lastName+" and c.lastName="+lastName,Client.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	public List<Client> findByAppointment(Appointment a) {
		List<Client> resultList = manager.createQuery("select c from Client c where c.id="+a.getClient().getId(),Client.class).getResultList();
		this.displayList(resultList);
		return resultList;
	}
	
	private void displayList(List<Client> resultList) {
		System.out.println("Clients Avaliable : "+resultList.size());
		for (Client appointment : resultList) {
			System.out.println("- Id of Client is "+appointment.getId());
		}
	}
}
