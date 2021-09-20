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

	public Agenda() {
		super();
	}

	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}


	
}
