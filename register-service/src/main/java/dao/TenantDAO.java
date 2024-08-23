package dao;

import domain.Tenant;

import java.util.Collection;

public interface TenantDAO {

    /**
     * Check if an existing username and password matches a tenant
     * @return Boolean true or flase, if values are matching or non-matching.
     */
    boolean checkTenantUsernaamePassword(String username, String password, Tenant tenant);

    /**
     * Method to get all tenants, primarily for testing
     * @return Collection of all tenants
     */
    Collection<Tenant> getAllTenants();

    /**
     * Method to remove a tenant, method primarly for testing purposes
     * @param tenant
     */
    void removeTenant(Tenant tenant);

    /**
     * Method to add a new tenant who creates an account.
     */
    void saveTenant(Tenant tenant);

    /**
     * Method to retrieve a tenant by their username
     */
    Tenant getTenantByUsername(String username);




}
