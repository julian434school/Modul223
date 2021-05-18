import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class PersonTest {

    static EntityManagerFactory entityManagerFactory;

    /**
     * Enter DB in console like this
     * Open Console in CMD
     * mysql -u root;
     * show databases;
     * use <db>;
     * show tables;
     * select * from <table>
     */

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("ch.gibm.hibernatetest");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        entityManagerFactory.close();
    }

    /*@Test
    public void savePersonObject() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        entityManager.getTransaction().commit();
        entityManager.close();
    }*/

    @Test
    public void savePersonObject() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person person = new Person();
        person.setFirstname("Hans");
        person.setLastname("Muster");
        person.setOrt("Basel");
        person.setPlz("4000");
        person.setStreet("Musterstrasse");
        person.setTelephonenumber("+41 111 11 11");

        entityManager.persist(person);

        entityManager.getTransaction().commit();
        entityManager.close();

        assertTrue(person.getId() > 0);
    }

    public Person findPerson(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // Objekt ist somit detached / nicht mehr managed und nicht mehr vom Entitymanager abhängig,
        // da mit Close die Transaktion geschlossen wird und das Objekt weiterhin besteht
        Person person = entityManager.find(Person.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return person;
    }

    @Test
    public void getFoundPerson() {
        //System.out.println(findPerson(1L));
        savePersonObject();
        assertEquals(findPerson(1L).getFirstname(), "Hans");
    }

    @Test
    public void updatePersonObject() {
        savePersonObject();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person person = entityManager.find(Person.class, 1L);
        person.setFirstname("Joe");

        entityManager.getTransaction().commit();
        entityManager.close();

        assertEquals(person.getFirstname(), "Joe");
    }

    @Test
    public void removePersonObject() {
        savePersonObject();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person person = entityManager.find(Person.class, 1L);
        entityManager.remove(person);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}