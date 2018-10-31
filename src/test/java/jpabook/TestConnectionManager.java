package jpabook;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestConnectionManager {

	private static final String PERSISTENCE_UNIT_NAME = "jpabook";

	private static EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;

	protected EntityTransaction entityTransaction;

	@BeforeClass
	public static void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@Before
	public void init() {
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	@After
	public void finalize() {
		entityManager.close();
	}

	@AfterClass
	public static void tearDown() {
		entityManagerFactory.close();
	}
}
