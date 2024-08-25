package dao;

import domain.Tenant;
import helpers.Argon2Helper;
import helpers.ScryptHelper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;

public interface TenantJdbiDAO extends TenantDAO {

//    @Override
//    @SqlQuery("SELECT EXISTS (SELECT * FROM Tenant "
//            + "WHERE Username = :username AND Password = :password)")
//    public boolean checkTenantUsernamePassword(@Bind("username") String username, @Bind("password") String password, Tenant tenant);

    @Override
    default boolean checkTenantUsernamePassword(String username, String password, Tenant tenant) {
        // Fetch tenant by username
        Tenant storedTenant = getTenantByUsername(username);
        if (storedTenant == null) {
            return false; // Username does not exist
        }

        // Retrieve the hashed password from the stored tenant
        String hashedPassword = storedTenant.getPassword();

        // Verify the provided password with the hashed password
        return Argon2Helper.verifyPassword(hashedPassword, password);
    }

    @Override
    @SqlQuery("SELECT * FROM Tenant ORDER BY username")
    @RegisterBeanMapper(Tenant.class)
    public Collection<Tenant> getAllTenants();

    @Override
    @SqlUpdate("DELETE FROM Tenant WHERE username = :username")
    public void removeTenant(@BindBean Tenant tenant);

    @Override
    default void saveTenant(Tenant tenant) {
        // Hash the password before saving the tenant
        String hashedPassword = Argon2Helper.hashPassword(tenant.getPassword());

        // Update the tenant's password with the hashed version
        tenant.setPassword(hashedPassword);

        // Proceed with saving the tenant (the @SqlUpdate method will be used for this)
        insertTenant(tenant);
    }

    @SqlUpdate("INSERT INTO Tenant (firstName, lastName, phoneNumber, username, password) " +
            "VALUES(:firstName, :lastName, :phoneNumber, :username, :password)")
    void insertTenant(@BindBean Tenant tenant);

    @Override
    @SqlQuery("SELECT * FROM Tenant WHERE username = :username")
    @RegisterBeanMapper(Tenant.class)
    public Tenant getTenantByUsername(@Bind("username") String username);
}
