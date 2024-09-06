package dao;

import domain.*;
import org.jdbi.v3.core.Handle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ManagementDAOTest {

    private ManagementDAO managementDAO;
    private Property property1;
    private Property property2;

    private Tenant tenant1;
    private Tenant tenant2;

    private Manager manager1;

    private Request request1;
    private Request request2;

    private Management management1;
    private Management management2;


    @BeforeAll
    public static void initialise() {
        JdbiDAOFactory.setJdbcUri("jdbc:postgresql://localhost:1244/tests");

        // Insert test data
        try (Handle handle = JdbiDAOFactory.getJdbi().open()) {

            handle.execute("DELETE FROM Tenant WHERE username = 'johndoe'");
            handle.execute("DELETE FROM Tenant WHERE username = 'janedoe'");
            handle.execute("INSERT INTO Tenant (firstName, lastName, phoneNumber, username, password) VALUES ('John', 'Doe', '123456789', 'johndoe', 'password')");
            handle.execute("INSERT INTO Tenant (firstName, lastName, phoneNumber, username, password) VALUES ('Jane', 'Doe', '987654321', 'janedoe', 'password')");

            handle.execute("DELETE FROM Manager WHERE username = 'janesmith'");
            handle.execute("INSERT INTO Manager (firstName, lastName, phoneNumber, username, password) VALUES ('Jane', 'Smith', '987654321', 'janesmith', 'password')");

            handle.execute("DELETE FROM Property WHERE address = 'Address 1'");
            handle.execute("DELETE FROM Property WHERE address = 'Address 2'");
            handle.execute("INSERT INTO Property (name, address, tenant_username, manager_username) VALUES ('Property 1', 'Address 1', 'johndoe', 'janesmith')");
            handle.execute("INSERT INTO Property (name, address, tenant_username, manager_username) VALUES ('Property 2', 'Address 2', 'janedoe', 'janesmith')");

            handle.execute("DELETE FROM Request WHERE name = 'Request 1'");
            handle.execute("DELETE FROM Request WHERE name = 'Request 2'");
            handle.execute("INSERT INTO Request (id, name, description, urgent, tenant_username, completed) VALUES ('R001','Request 1', 'Description 1', true, 'johndoe', false)");
            handle.execute("INSERT INTO Request (id, name, description, urgent, tenant_username, completed) VALUES ('R002','Request 2', 'Description 2', false, 'janedoe', false)");

        }
    }

    @BeforeEach
    void setUp() {
        managementDAO = JdbiDAOFactory.getManagementDAO();

        tenant1 = new Tenant("John", "Doe", "123456789", "johndoe", "password");
        tenant2 = new Tenant("Jane", "Doe", "987654321", "janedoe", "password");

        manager1 = new Manager("Jane", "Smith", "987654321", "janesmith", "password");

        property1 = new Property("Property 1", "Address 1", tenant1.getUsername(), manager1);
        property2 = new Property("Property 2", "Address 2", tenant2.getUsername(), manager1);

        request1 = new Request( "Request 1", "Description 1", true, tenant1, false, property1);
        request2 = new Request( "Request 2", "Description 2", false, tenant2, false, property2);

//        management1 = new Management("Job 1", "Description 1", property1, request1, "Pending", LocalDateTime.MAX);

        managementDAO.createJob(management1);
    }

    @AfterEach
    void tearDown() {
        managementDAO.deleteJob(management1);
        managementDAO.deleteJob(management2);
    }

    @Test
    void createJob() {

    }

    @Test
    void updateJob() {
    }

    @Test
    void deleteJob() {
    }

    @Test
    void getJobByPropertyAddress() {
    }

    @Test
    void getJobByRequestId() {
    }
}