package dao;

import domain.Tenant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class TenantDAOTest {

    private TenantDAO tenantDAO;
    private Tenant tenant1;
    private Tenant tenant2;
    private Tenant tenant3;

    @BeforeEach
    void setUp() {

        tenantDAO = new TenantCollectionsDAO();
        tenant1 = new Tenant("0001", "John", "Doe", "020321456", "john", "password");
        tenant2 = new Tenant("0002", "Jane", "Doe", "020321456", "jane", "password");
        tenant3 = new Tenant("0003", "Jack", "Doe", "020321456", "jack", "password");

        tenantDAO.saveTenant(tenant1);
        tenantDAO.saveTenant(tenant2);
    }

    @AfterEach
    void tearDown() {
        tenantDAO.removeTenant(tenant1);
        tenantDAO.removeTenant(tenant2);
        tenantDAO.removeTenant(tenant3);
    }

    @Test
    void checkTenantUsernamePassword() {
        //Check the username and password, if it returns true then there is match

        //First check that the tenant1 is in storage and there are two items
        assertThat(tenantDAO.getAllTenants(), hasItem(tenant1));
        assertThat(tenantDAO.getAllTenants(), hasSize(2));

        //Define Username and password
        String username = "john";
        String password = "password";

        //Run the check username and password method, with correct information.
        assertThat(tenantDAO.checkTenantUsernaamePassword(username, password, tenant1), is(true));

        //Run the check username and password method, with incorrect information.
        assertThat(tenantDAO.checkTenantUsernaamePassword(username, "wrongpassword", tenant1), is(false));
        assertThat(tenantDAO.checkTenantUsernaamePassword("wrongusername", password, tenant1), is(false));


    }

    @Test
    void getAllTenants() {
        //Check that the DAO contains the tenants
        assertThat(tenantDAO.getAllTenants(), hasItem(tenant1));
        assertThat(tenantDAO.getAllTenants(), hasItem(tenant2));
        assertThat(tenantDAO.getAllTenants(), hasSize(2));

        //check tenant 3 is not in the tenantDAO
        assertThat(tenantDAO.getAllTenants(), not(hasItem(tenant3)));

    }

    @Test
    void removeTenant() {

       //Check that the tenant to be deleted is in the DAO
        assertThat(tenantDAO.getAllTenants(), hasItem(tenant1));
        assertThat(tenantDAO.getAllTenants(), hasSize(2));

        //Remove the tenant
        tenantDAO.removeTenant(tenant1);

        //Check that the tenant has been removed
        assertThat(tenantDAO.getAllTenants(), not(hasItem(tenant1)));
        assertThat(tenantDAO.getAllTenants(), hasSize(1));

    }

    @Test
    void saveTenant() {

        //First check the DAO does not contain the tenant3
        assertThat(tenantDAO.getAllTenants(), not(hasItem(tenant3)));
        assertThat(tenantDAO.getAllTenants(), hasSize(2));

        //Add the tenant
        tenantDAO.saveTenant(tenant3);

        //Check the tenant has been added
        assertThat(tenantDAO.getAllTenants(), hasItem(tenant3));
        assertThat(tenantDAO.getAllTenants(), hasSize(3));

    }

    @Test
    void getTenantByUsername() {

        //Test the method on tenant2
        // check that tenant3 is not in the DAO
        assertThat(tenantDAO.getTenantByUsername("jack"), is(nullValue()));

        //Check both the usernames for tenant1 and tenant2 are in the DAO
        assertThat(tenantDAO.getTenantByUsername("john"), is(tenant1));
        assertThat(tenantDAO.getTenantByUsername("jane"), is(tenant2));

        //Check that there are no overidden Ids.
        assertThat(tenantDAO.getTenantByUsername("john"), is(not(tenant2)));
        assertThat(tenantDAO.getTenantByUsername("jane"), is(not(tenant1)));


    }
}