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
	

	public Prestataire() {
		super();
	}
	
	public Prestataire(String firstName, String lastName, String email, String password,Entreprise entreprise) {
		super(firstName,lastName,email,password);
		this.entreprise = entreprise;
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

	
}
