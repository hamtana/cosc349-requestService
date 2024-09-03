package dao;

import domain.Manager;
import domain.Tenant;
import helpers.Argon2Helper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;

public interface ManagerJdbiDAO extends ManagerDAO {

    @Override
    default boolean checkManagerUsernamePassword(String username, String password, Manager manager) {
        // Fetch tenant by username
        Manager storedManager= getManagerByUsername(username);
        if (storedManager == null) {
            return false; // Username does not exist
        }

        // Retrieve the hashed password from the stored tenant
        String hashedPassword = storedManager.getPassword();

        // Verify the provided password with the hashed password
        return Argon2Helper.verifyPassword(hashedPassword, password);
    }

    @Override
    @SqlQuery("SELECT * FROM Manager ORDER BY username")
    @RegisterBeanMapper(Manager.class)
    Collection<Manager> getAllManagers();

    @Override
    @SqlUpdate("DELETE FROM Manager WHERE username = :username")
    void removeManager(@BindBean Manager manager);

    @Override
    default void saveManager(Manager manager){
        // Hash the password before saving the manager
        String hashedPassword = Argon2Helper.hashPassword(manager.getPassword());

        // Update the tenant's password with the hashed version
        manager.setPassword(hashedPassword);

        // Proceed with saving the tenant (the @SqlUpdate method will be used for this)
        insertManager(manager);
    }

    @SqlUpdate("INSERT INTO Manager (firstName, lastName, phoneNumber, username, password) " +
            "VALUES(:firstName, :lastName, :phoneNumber, :username, :password)")
    void insertManager(@BindBean Manager manager);

    @Override
    @SqlQuery("SELECT * FROM Manager WHERE username = :username")
    @RegisterBeanMapper(Manager.class)
    Manager getManagerByUsername(@Bind("username") String username);
}
