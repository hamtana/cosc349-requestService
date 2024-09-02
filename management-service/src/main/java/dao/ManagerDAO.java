package dao;

import domain.Manager;

import java.util.Collection;

public interface ManagerDAO {
    boolean checkManagerUsernamePassword(String username, String password, Manager manager);

    Collection<Manager> getAllManagers();

    void removeManager(Manager manager);

    void saveManager(Manager manager);

    Manager getManagerByUsername(String username);
}
