package dao;

import domain.Manager;
import org.jdbi.v3.core.Handle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class ManagerDAOTest {

    private ManagerDAO managerDAO;
    private Manager manager1;
    private Manager manager2;
    private Manager manager3;

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
        managerDAO = JdbiDAOFactory.getManagerDAO();
        manager1 = new Manager("John", "Doe", "123456789", "johndoe", "password");
        manager2 = new Manager("Jane", "Doe", "987654321", "janedoe", "password");
        manager3 = new Manager("John", "Smith", "123456789", "johnsmith", "password");

        managerDAO.saveManager(manager1);
        managerDAO.saveManager(manager2);
    }

    @AfterEach
    void tearDown() {
        managerDAO.removeManager(manager1);
        managerDAO.removeManager(manager2);
        managerDAO.removeManager(manager3);
    }

    @Test
    void checkManagerUsernamePassword() {

        assertThat(managerDAO.getAllManagers(), hasItem(manager1));
        assertThat(managerDAO.getAllManagers(), hasSize(2));

        //Define a username and password
        String username = "johndoe";
        String password = "password";

        //Run the check username and password method, with correct information.
        assertThat(managerDAO.checkManagerUsernamePassword(username, password, manager1), is(true));

        //Run the check username and password method, with incorrect information.
        assertThat(managerDAO.checkManagerUsernamePassword(username, "wrongpassword", manager1), is(false));
        assertThat(managerDAO.checkManagerUsernamePassword("wrongusername", password, manager1), is(false));

    }

    @Test
    void getAllManagers() {
        assertThat(managerDAO.getAllManagers(), hasItem(manager1));
        assertThat(managerDAO.getAllManagers(), hasItem(manager2));
        assertThat(managerDAO.getAllManagers(), hasSize(2));
    }

    @Test
    void removeManager() {

        //check that the manager1 (to be deleted) is in storage
        assertThat(managerDAO.getAllManagers(), hasItem(manager1));
        assertThat(managerDAO.getAllManagers(), hasSize(2));

        //Delete the Manager
        managerDAO.removeManager(manager1);

        //Check that the manager has been removed
        assertThat(managerDAO.getAllManagers(), not(hasItem(manager1)));
        assertThat(managerDAO.getAllManagers(), hasSize(1));
    }

    @Test
    void saveManager() {

        //check the dao does not contain manager3
        assertThat(managerDAO.getAllManagers(), hasSize(2));
        assertThat(managerDAO.getAllManagers(), not(hasItem(manager3)));

        //add the manager
        managerDAO.saveManager(manager3);

        //check the dao contains manager3
        assertThat(managerDAO.getAllManagers(), hasSize(3));
        assertThat(managerDAO.getAllManagers(), hasItem(manager3));


    }

    @Test
    void getManagerByUsername() {

        //test the method on manager2
        assertThat(managerDAO.getManagerByUsername("janedoe"), is(manager2));

        //check both the usernames for manager1 and manager2 are in the dao
        assertThat(managerDAO.getAllManagers(), hasItem(manager1));
        assertThat(managerDAO.getAllManagers(), hasItem(manager2));

        //check that there are no overidden usernames
        assertThat(managerDAO.getManagerByUsername("johndoe"), is(not(manager2)));
        assertThat(managerDAO.getManagerByUsername("janedoe"), is(not(manager1)));
    }
}