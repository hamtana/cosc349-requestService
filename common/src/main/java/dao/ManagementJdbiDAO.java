package dao;

import domain.Management;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface ManagementJdbiDAO extends ManagementDAO {

    @Override
    @SqlUpdate("INSERT INTO Management" +
            "(name, description, property_address, status, date) " +
            "VALUES (:name, :description, :property.address :status, :date)")
    void createJob(@BindBean Management management);

    @Override
    @SqlUpdate("UPDATE Management SET name = :name, description = :description," +
            " status = :status, date = :date WHERE property_address = :property.address")
    void updateJob(@BindBean Management management);

    @Override
    @SqlUpdate("DELETE FROM Management WHERE property_address = :property.address")
    void deleteJob(Management management);

    @Override
    @SqlQuery("SELECT * FROM Management WHERE property_address = :address")
    @RegisterBeanMapper(Management.class)
    Management getJobByPropertyAddress(@Bind("address") String address);


}
