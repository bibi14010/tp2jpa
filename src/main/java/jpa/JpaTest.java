package jpa;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import jpa.objects.Appointment;
import jpa.objects.Client;
import jpa.objects.Entreprise;
import jpa.objects.Location;
import jpa.objects.Prestataire;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();


		try {
			Location l = new Location("Rue du cratère",2,1256,"Base Lunaire 8851");
			manager.persist(l);
			
			Entreprise e = new Entreprise("Mechant Industries",0,l);
			manager.persist(e);
			
			
			Prestataire p = new Prestataire("Borice","Lanimal","borice.lanimale@mechant.mib","agentK",e);
			manager.persist(p);
			
			Client c = new Client("Agent","K","agent.k@gentil.mib","boricelanimal",0);
			manager.persist(c);
			
			Appointment rdv = new Appointment(LocalDate.now(), c,p,l,30,0, null);
			manager.persist(rdv);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	}


}
