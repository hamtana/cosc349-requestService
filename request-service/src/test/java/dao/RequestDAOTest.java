package dao;

import domain.Manager;
import domain.Property;
import domain.Request;
import domain.Tenant;
import org.junit.jupiter.api.AfterEach;
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


    @BeforeEach
    void setUp() {
        requestDAO = new RequestCollectionsDAO();

        manager = new Manager( "Steve", "Jobs", "020321456", "steve", "password");
        tenant = new Tenant("John", "Doe", "020321456", "john", "password");
        property = new Property("0001", "The White House", "12 North Rd", tenant, manager);

        request1 = new Request("0001", "Broken Toilet", "The toilet is broken", true, property, tenant, false);
        request2 = new Request("0002", "Broken Window", "The window is broken", false, property, tenant, false);
        request3 = new Request("0003", "Broken Door", "The door is broken", true, property, tenant, false);
        request4 = new Request("0004", "Broken Roof", "The roof is broken", false, property, tenant, false);

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
    void getRequestById() {

        //Check that the following ids are included in the system
        assertThat(requestDAO.getRequestById("0001"), is(request1));
        assertThat(requestDAO.getRequestById("0002"), is(request2));

        //Check that no ids have been overwritten
        assertThat(requestDAO.getRequestById("0001"), is(not(request2)));
        assertThat(requestDAO.getRequestById("0002"), is(not(request1)));

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
        assertThat(requestDAO.getRequestById("0004").getName(), is("New Name"));
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