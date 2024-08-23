package dao;

import domain.Manager;
import domain.Property;
import domain.Request;
import domain.Tenant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


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
        manager = new Manager("0001", "Steve", "Jobs", "020321456");
        tenant = new Tenant("0001", "John", "Doe", "020321456" );
        property = new Property("0001", "The White House", manager, tenant);

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
        requestDAO.delete(request1);
        requestDAO.delete(request2);
        requestDAO.delete(request3);
        requestDAO.delete(request4);
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
    }

    @Test
    void createRequest() {
    }

    @Test
    void updateRequest() {
    }

    @Test
    void delete() {
    }
}