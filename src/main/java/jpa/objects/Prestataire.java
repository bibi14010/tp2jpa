package jpa.objects;

import javax.persistence.*;

@Entity
@Table(name="Prestataire")
public class Prestataire extends Personne {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(targetEntity=Entreprise.class, fetch=FetchType.EAGER)
	private Entreprise entreprise;
	
	@OneToOne(targetEntity=Agenda.class, fetch=FetchType.EAGER)
	private Agenda agenda;

	public Prestataire() {
		super();
	}
	
	public Prestataire(Entreprise entreprise, Agenda agenda) {
		super();
		this.entreprise = entreprise;
		this.agenda = agenda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
}
