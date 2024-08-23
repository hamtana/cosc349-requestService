package dao;

import domain.Tenant;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TenantCollectionsDAO implements TenantDAO{

    private static final Map<String, Tenant> tenants = new HashMap<>();

    @Override
    public boolean checkTenantUsernaamePassword(String username, String password, Tenant tenant) {
        return tenant.getUsername().equals(username) && tenant.getPassword().equals(password);
    }

    @Override
    public Collection<Tenant> getAllTenants() {
        return tenants.values();
    }

    @Override
    public void removeTenant(Tenant tenant) {
        tenants.remove(tenant.getUsername());
    }

    @Override
    public void saveTenant(Tenant tenant) {
        tenants.put(tenant.getUsername(), tenant);
    }

    @Override
    public Tenant getTenantByUsername(String username) {
        return tenants.get(username);
    }

}
