package jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class GenericDao {
	
	private EntityManager manager;
	
	public GenericDao(EntityManager manager) {
		this.manager = manager;
	}

}
