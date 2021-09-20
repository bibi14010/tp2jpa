package jpa.objects;

import javax.persistence.*;

@Entity
@Table(name="Entreprises")
public class Entreprise {
	

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private int sector;
	
	@OneToOne
	private Location location;

	public Entreprise() {
		super();
	}

	public Entreprise(String name, int sector, Location location) {
		super();
		this.name = name;
		this.sector = sector;
		this.location = location;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSector() {
		return sector;
	}

	public void setSector(int sector) {
		this.sector = sector;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
