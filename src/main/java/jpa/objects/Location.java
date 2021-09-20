package jpa.objects;

import javax.persistence.*;

@Entity
@Table(name="Locations")
@NamedQueries({
	@NamedQuery(name="toutesLesLocations",query="select l from Location l"),
	@NamedQuery(name="toutesLesLocationsParVille",query="select l from Location l where l.city=:cityWanted")
})
public class Location {
	
	public Location() {
		super();
	}
	
	public Location(String street, int streetNumber, int postCode, String city) {
		super();
		this.street = street;
		this.streetNumber = streetNumber;
		this.postCode = postCode;
		this.city = city;
	}

	@Id
	@GeneratedValue
	private int id;
	
	private String street;
	
	private int streetNumber;
	
	private int postCode;
	
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
