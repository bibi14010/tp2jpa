package jpa.objects;

import javax.persistence.*;

@Entity
@Table(name="Clients")
public class Client extends Personne{

	@Id
	@GeneratedValue
	private int id;
	
	private int numberOfAppointment;

	public Client(String firstName, String lastName, String email, String password, int numberOfAppointment) {
		super(firstName,lastName,email,password);
		this.numberOfAppointment = numberOfAppointment;
	}

	public Client() {
		super();
		this.numberOfAppointment=0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNumberOfAppointment() {
		return numberOfAppointment;
	}

	public void setNumberOfAppointment(int numberOfAppointment) {
		this.numberOfAppointment = numberOfAppointment;
	}
	
	
}
