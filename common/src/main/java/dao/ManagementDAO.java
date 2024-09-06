package dao;

import domain.Management;

public interface ManagementDAO {

    void createJob(Management management);

    void updateJob(Management management);

    void deleteJob(Management management);

    Management getJobByPropertyAddress(String address);

}
