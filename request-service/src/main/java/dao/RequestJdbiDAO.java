package dao;

import domain.Request;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;

public interface RequestJdbiDAO extends RequestDAO{

    @Override
    @SqlQuery("SELECT * FROM Request ORDER BY id")
    @RegisterBeanMapper(Request.class)
    public Collection<Request> getAllRequests();

    @Override
    @SqlQuery("SELECT * FROM Request WHERE id = :id")
    @RegisterBeanMapper(Request.class)
    public Request getRequestById(@Bind("id") String id);

    @Override
    @SqlUpdate("INSERT INTO Request (id, name, description, urgent, property_address, tenant_username, completed) " +
            "VALUES(:id, :name, :description, :urgent, :property.address, :tenant.username, :completed)")
    @GetGeneratedKeys
    public void createRequest(@BindBean Request request);

    @Override
    @SqlUpdate("INSERT INTO Request (id, name, description, urgent, property_address, tenant_username, completed) " +
            "VALUES(:id, :name, :description, :urgent, :property.address, :tenant.username, :completed)")
    @GetGeneratedKeys
    public void updateRequest(@BindBean Request request);

    @Override
    @SqlUpdate("DELETE FROM Request WHERE id = :id")
    public void deleteRequest(@BindBean Request request);
}
