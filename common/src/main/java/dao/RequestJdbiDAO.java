package dao;

import domain.Request;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;

public interface RequestJdbiDAO extends RequestDAO{

    @SqlQuery("SELECT * FROM Request ORDER BY id")
    @RegisterBeanMapper(Request.class)
    public Collection<Request> getAllRequests();

    @SqlQuery("SELECT * FROM Request WHERE tenant_username = :tenant")
    @RegisterBeanMapper(Request.class)
    public Collection<Request> getRequestByTenant(@Bind("tenant") String username);

    @SqlQuery("SELECT * FROM Request WHERE name = :name")
    @RegisterBeanMapper(Request.class)
    public Request getRequestByName(@Bind("name") String name);


    @SqlUpdate("INSERT INTO Request (name, description, urgent, tenant_username, completed, property_address) " +
            "VALUES(:name, :description, :urgent, :tenant.username, :completed, :property.address)")
    Integer createRequest(@BindBean Request request);


    @SqlUpdate("UPDATE Request SET description = :description, urgent = :urgent, " +
            "tenant_username = :tenant.username, property_address = :property.address, completed = :completed WHERE name = :name")
    public void updateRequest(@BindBean Request request);

    @SqlUpdate("DELETE FROM Request WHERE name = :name")
    public void deleteRequest(@BindBean Request request);
}

