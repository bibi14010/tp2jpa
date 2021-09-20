package jpa.objects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Agendas")
public class Agenda {
	
	@Id
	@GeneratedValue
	private int Id;
	
	@ElementCollection(targetClass=Appointment.class)
	private List<Appointment> appointments = new ArrayList<Appointment>();

	public Agenda(List<Appointment> appointments) {
		super();
		this.appointments = appointments;
	}

	public Agenda() {
		super();
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	
}
