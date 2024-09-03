package dao;

import domain.Manager;
import domain.Property;
import domain.Request;
import domain.Tenant;
import org.jdbi.v3.core.Handle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;


public class RequestDAOTest {

    private RequestDAO requestDAO;
    private Request request1;
    private Request request2;
    private Request request3;
    private Request request4;

    private Tenant tenant;
    private Property property;
    private Manager manager;

    @BeforeAll
    public static void initialise() {
        JdbiDAOFactory.setJdbcUri("jdbc:postgresql://localhost:1244/tests");

        // Insert test data
        try (Handle handle = JdbiDAOFactory.getJdbi().open()) {
            handle.execute("DELETE FROM Tenant WHERE username = 'john'");
            handle.execute("INSERT INTO Tenant (firstName, lastName, phoneNumber, username, password) VALUES ('John', 'Doe', '020321456', 'john', 'password')");

            handle.execute("DELETE FROM Manager WHERE username = 'steve'");
            handle.execute("INSERT INTO Manager (firstName, lastName, phoneNumber, username, password) VALUES ('Steve', 'Jobs', '020321456', 'steve', 'password')");

            handle.execute("DELETE FROM Property WHERE address = '12 North Rd'");
            handle.execute("INSERT INTO Property (name, address, tenant_username, manager_username) VALUES ('The White House', '12 North Rd', 'john', 'steve')");
        }
    }


    @BeforeEach
    void setUp() {
        requestDAO = JdbiDAOFactory.getRequestDAO();
//      requestDAO = new RequestCollectionsDAO();

        manager = new Manager( "Steve", "Jobs", "020321456", "steve", "password");
        tenant = new Tenant("John", "Doe", "020321456", "john", "password");
        property = new Property("The White House", "12 North Rd", tenant.getUsername(), manager);

        request1 = new Request("Broken Toilet", "The toilet is broken", true, tenant, false, property);
        request2 = new Request("Broken Window", "The window is broken", false, tenant, false, property);
        request3 = new Request("Broken Door", "The door is broken", true, tenant, false, property);
        request4 = new Request("Broken Roof", "The roof is broken", false, tenant, false,property);

        //Add tests to the requestDAO
        requestDAO.createRequest(request1);
        requestDAO.createRequest(request2);
        requestDAO.createRequest(request3);
    }

    @AfterEach
    void tearDown() {
        requestDAO.deleteRequest(request1);
        requestDAO.deleteRequest(request2);
        requestDAO.deleteRequest(request3);
        requestDAO.deleteRequest(request4);
    }

    @Test
    void getAllRequests() {
        assertThat(requestDAO.getAllRequests(), not(hasItem(request4)));
        assertThat(requestDAO.getAllRequests(), hasSize(3)); //size should be 3

        // add the fourth request
        requestDAO.createRequest(request4);
        assertThat(requestDAO.getAllRequests(), hasSize(4)); //size should be 4

    }

    @Test
    void getRequestsByTenant() {

        assertThat(requestDAO.getRequestByTenant(request1.getTenant().getUsername()), hasItem(request1));
        assertThat(requestDAO.getRequestByTenant(request1.getTenant().getUsername()), hasSize(3));

        //Check that the following ids are included in the system
//        assertThat(requestDAO.getRequestByTenant(request1.getTenant().getUsername()), is(request1));
//        assertThat(requestDAO.getRequestByTenant(request1.getTenant().getUsername()), hasProperty(request1.getTenant().getUsername()));
//        assertThat(requestDAO.getRequestByTenant(request2.getTenant().getUsername()), is(request2));

        //Check that no ids have been overwritten
//        assertThat(requestDAO.getRequestByTenant(request1.getTenant().getUsername()), not(hasItem(request2)));
//        assertThat(requestDAO.getRequestByTenant(request2.getTenant().getUsername()), not(hasItem(request1)));
    }

    @Test
    void getRequestById(){
        assertThat(requestDAO.getRequestByName(request1.getName()), is(request1));
        assertThat(requestDAO.getRequestByName(request2.getName()), is(request2));
        assertThat(requestDAO.getRequestByName(request3.getId()), is(request3));
    }

    @Test
    void createRequest() {
        //First check that the request added does not exist
        assertThat(requestDAO.getAllRequests(), not(hasItem(request4)));
        assertThat(requestDAO.getAllRequests(), hasSize(3)); //size should be 3

        //Add the request
        requestDAO.createRequest(request4);
        assertThat(requestDAO.getAllRequests(), hasItem(request4));
        assertThat(requestDAO.getAllRequests(), hasSize(4)); //size should be 4
    }

    @Test
    void updateRequest() {
        //First check that the request added does not exist
        assertThat(requestDAO.getAllRequests(), not(hasItem(request4)));
        assertThat(requestDAO.getAllRequests(), hasSize(3)); //size should be 3

        //Add the request
        requestDAO.createRequest(request4);
        assertThat(requestDAO.getAllRequests(), hasItem(request4));
        assertThat(requestDAO.getAllRequests(), hasSize(4)); //size should be 4

        //Update the request
        request4.setName("New Name");
        requestDAO.updateRequest(request4);
        assertThat(requestDAO.getAllRequests(), hasItem(request4));
    }

    @Test
    void deleteRequest() {

        //First check the request to be deleted is within the DAO
        assertThat(requestDAO.getAllRequests(), hasItem(request1));
        assertThat(requestDAO.getAllRequests(), hasSize(3)); //size should be 3

        //Delete the request #1

        requestDAO.deleteRequest(request1);
        assertThat(requestDAO.getAllRequests(), not(hasItem(request1)));

        //check the delete method did not delete everything
        assertThat(requestDAO.getAllRequests(), hasSize(2)); //size should be 2

    }
}