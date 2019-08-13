package com.broodcamp.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Edward P. Legaspi <czetsuya@gmail.com>
 * 
 * Manage the persistence. It uses the configuration in persistence.xml file.
 */
public enum PersistenceManager {
	INSTANCE;

	private EntityManagerFactory emFactory;

	private PersistenceManager() {
		emFactory = Persistence.createEntityManagerFactory("BroodcampPU");
	}

	public EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public void close() {
		emFactory.close();
	}
}
