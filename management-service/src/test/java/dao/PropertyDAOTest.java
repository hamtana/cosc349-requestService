package dao;

import domain.Manager;
import domain.Property;
import domain.Tenant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PropertyDAOTest {

    private PropertyDAO propertyDAO;
    private Property property1;
    private Property property2;
    private Property property3;

    @BeforeAll
    public static void initialise() {
        JdbiDAOFactory.setJdbcUri("jdbc:postgresql://localhost:1244/tests");

//        // Insert test data
//        try (Handle handle = JdbiDAOFactory.getJdbi().open()) {
//            handle.execute("DELETE FROM Tenant WHERE username = 'john'");
//            handle.execute("INSERT INTO Tenant (firstName, lastName, phoneNumber, username, password) VALUES ('John', 'Doe', '020321456', 'john', 'password')");
//        }
    }


    @BeforeEach
    void setUp() {
//        propertyDAO = new PropertyCollectionsDAO();
        propertyDAO = JdbiDAOFactory.getPropertyDAO();

        Tenant tenant1 = new Tenant("John", "Doe", "123456789", "johndoe", "password");
        Tenant tenant2 = new Tenant("Jane", "Doe", "987654321", "janedoe", "password");
        Tenant tenant3 = new Tenant("John", "Smith", "123456789", "johnsmith", "password");

        Manager manager1 = new Manager("Jane", "Smith", "987654321", "janesmith", "password");

        property1 = new Property(1, "Property 1", "Address 1", tenant1, manager1);
        property2 = new Property(2, "Property 2", "Address 2", tenant2, manager1);
        property3 = new Property(3, "Property 3", "Address 3", tenant3, manager1);

        propertyDAO.createProperty(property1);
        propertyDAO.createProperty(property2);

    }

    @AfterEach
    void tearDown() {
        propertyDAO.deleteProperty(property1);
        propertyDAO.deleteProperty(property2);
        propertyDAO.deleteProperty(property3);
    }

    @Test
    void createProperty() {
        //check that property3 does not already exist
        assertThat(propertyDAO.getAllProperties(), hasSize(2));
        assertThat(propertyDAO.getAllProperties(), not(hasItem(property3)));

        //create property3
        propertyDAO.createProperty(property3);

        //check propertyDAO contains Property3
        assertThat(propertyDAO.getAllProperties(), hasSize(3));
        assertThat(propertyDAO.getAllProperties(), hasItem(property3));

    }

    @Test
    void updateProperty() {
        //check that property1 has the correct name
        assertThat(propertyDAO.getPropertyByName("Property 1").getName(), is("Property 1"));

        //change the name of property1
        property1.setName("New Property 1");
        propertyDAO.updateProperty(property1);

        //check that property1 has the new name
        assertThat(propertyDAO.getPropertyByName("New Property 1").getName(), is("New Property 1"));
    }

    @Test
    void deleteProperty() {
        //check that property1 exists
        assertThat(propertyDAO.getAllProperties(), hasSize(2));
        assertThat(propertyDAO.getAllProperties(), hasItem(property1));

        //delete property1
        propertyDAO.deleteProperty(property1);

        //check that property1 has been deleted
        assertThat(propertyDAO.getAllProperties(), hasSize(1));
        assertThat(propertyDAO.getAllProperties(), not(hasItem(property1)));

    }

    @Test
    void getPropertyByName() {
        //check that property1 is in the DAO
        assertThat(propertyDAO.getPropertyByName("Property 1"), is(property1));
    }

    @Test
    void getAllProperties() {
        //check that all properties are in the DAO
        assertThat(propertyDAO.getAllProperties(), hasItem(property1));
        assertThat(propertyDAO.getAllProperties(), hasItem(property2));
        assertThat(propertyDAO.getAllProperties(), hasSize(2));
    }

    @Test
    void getPropertiesByManagerUsername() {
        //check that property1 and property2 are in the DAO
        assertThat(propertyDAO.getPropertiesByManagerUsername("janesmith"), hasItem(property1));
        assertThat(propertyDAO.getPropertiesByManagerUsername("janesmith"), hasItem(property2));
        assertThat(propertyDAO.getPropertiesByManagerUsername("janesmith"), hasSize(2));

    }
}