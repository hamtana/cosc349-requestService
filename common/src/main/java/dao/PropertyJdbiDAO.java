package dao;

import domain.Property;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;

public interface PropertyJdbiDAO extends PropertyDAO {

    @Override
    @SqlUpdate("INSERT INTO Property" +
            "(name, address, tenant_username, manager_username) " +
            "VALUES (:name, :address, :tenant.username, :manager.username)")
    void createProperty(@BindBean Property property);

    @Override
    @SqlUpdate("UPDATE Property SET name = :name, address = :address," +
            " tenant_username = :tenant.username, " +
            "manager_username = :manager.username WHERE address = :address")
    void updateProperty(@BindBean Property property);

    @Override
    @SqlUpdate("DELETE FROM Property WHERE address = :address")
    void deleteProperty(@BindBean Property property);

    @Override
    @SqlQuery("SELECT * FROM Property WHERE address = :address")
    @RegisterBeanMapper(Property.class)
    Property getPropertyByAddress(@Bind("address") String address);

    @Override
    @SqlQuery("SELECT * FROM Property ORDER BY id")
    @RegisterBeanMapper(Property.class)
    Collection<Property> getAllProperties();

    @Override
    @SqlQuery("SELECT * FROM Property WHERE manager_username = :username")
    @RegisterBeanMapper(Property.class)
    Collection<Property> getPropertiesByManagerUsername(@Bind("username") String username);
}
