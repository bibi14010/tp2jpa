package jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;

import jpa.objects.Agenda;

public class AgendaDao {

	private EntityManager manager;

	public AgendaDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void find() {
		List<Agenda> resultList = manager.createQuery("Select a from Agenda a", Agenda.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByEmail(String email) {
		List<Agenda> resultList = manager.createQuery("Select p.agenda from Prestataire p where p.email="+email,Agenda.class).getResultList();
		this.displayList(resultList);
	}
	
	public void findByAppointment(int id) {
		List<Agenda> resultList = manager.createQuery("Select a from Agenda a where a.id="+id,Agenda.class).getResultList();
		System.out.println("Agendas Avaliable : "+resultList.size());
		for (Agenda agenda : resultList) {
			if(agenda.getId()==id) {
				System.out.println("The agenda "+id+" has beenb found.");
				}
			}
	}
	
	private void displayList(List<Agenda> resultList) {
		System.out.println("Agendas Avaliable : "+resultList.size());
		for (Agenda agenda : resultList) {
			System.out.println("- Id of Agenda is "+agenda.getId());
		}
	}
}
