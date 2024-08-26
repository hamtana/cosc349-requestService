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

    @SqlQuery("SELECT * FROM Request ORDER BY id")
    @RegisterBeanMapper(Request.class)
    public Collection<Request> getAllRequests();

    @SqlQuery("SELECT * FROM Request WHERE tenant_username = :tenant")
    @RegisterBeanMapper(Request.class)
    public Request getRequestByTenant(@Bind("tenant") String username);


    @SqlUpdate("INSERT INTO Request (id, name, description, urgent, tenant_username, completed) " +
            "VALUES(:id, :name, :description, :urgent, :tenant.username, :completed)")
    Integer createRequest(@BindBean Request request);


    @SqlUpdate("UPDATE Request SET name = :name, description = :description, urgent = :urgent, " +
            "tenant_username = :tenant.username, completed = :completed WHERE id = :id")
    public void updateRequest(@BindBean Request request);

    public void deleteRequest(Request request);
}

