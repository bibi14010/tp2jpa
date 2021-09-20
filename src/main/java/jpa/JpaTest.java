package jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import jpa.objects.Agenda;
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
			Location l = new Location("Rue des bateliers",2,35580,"Saint-Senoux");
			manager.persist(l);
			
			Entreprise e = new Entreprise("Atelier Senonais",0,l);
			manager.persist(e);
			
			Agenda a = new Agenda();
			manager.persist(a);
			
			Prestataire p = new Prestataire("Borice","Lanimal","borice.lanimale@mechant.mib","agentK",e,a);
			manager.persist(p);
			
			Client c = new Client("Agent","K","agent.k@gentil.mib","boricelanimal",0);
			manager.persist(c);
			
			Appointment rdv = new Appointment(new Date(), c,p,l,30,0, null);
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
