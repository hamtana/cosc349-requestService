package dao;

import domain.Manager;
import domain.Property;
import domain.Request;
import domain.Tenant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;


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
        manager = new Manager("0001", "John", "Doe", "020321456");
        tenant = new Tenant("0001", "John", "Doe", "020321456" );
        property = new Property("0001", "The White House", manager, tenant);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllRequests() {
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