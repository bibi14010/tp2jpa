package jpa.objects;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Appointments")
public class Appointment {
	
	@Id
	@GeneratedValue
	private int Id;
	
	@Temporal(value = TemporalType.DATE)
	private Date date;
	
	@ManyToOne(targetEntity=Client.class, fetch=FetchType.EAGER)
	private Client client;
	
	@ManyToOne(targetEntity=Prestataire.class, fetch=FetchType.EAGER)
	private Prestataire prestataire;
	
	@ManyToOne(targetEntity=Location.class, fetch=FetchType.EAGER)
	private Location location;
	
	// Dur�e en minute du rendez-vous
	private int time;
	
	// �tats : pr�vu -> valid� -> pass�
	private int statut;
	
	private String description;

	public Appointment(Date date, Client client, Prestataire prestataire, Location location, int time, int statut,
			String description) {
		super();
		this.date = date;
		this.client = client;
		this.prestataire = prestataire;
		this.location = location;
		this.time = time;
		this.statut = statut;
		this.description = description;
	}

	public Appointment() {
		super();
	}
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Prestataire getPrestataire() {
		return prestataire;
	}

	public void setPrestataire(Prestataire prestataire) {
		this.prestataire = prestataire;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
