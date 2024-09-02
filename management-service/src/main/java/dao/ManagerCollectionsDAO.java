package dao;

import domain.Manager;

import java.util.Collection;
import java.util.HashMap;

public class ManagerCollectionsDAO implements ManagerDAO {

    private static final HashMap <String, Manager> managers = new HashMap<>();

    @Override
    public boolean checkManagerUsernamePassword(String username, String password, Manager manager) {
        return manager.getUsername().equals(username) && manager.getPassword().equals(password);
    }

    @Override
    public Collection<Manager> getAllManagers() {
        return managers.values();
    }

    @Override
    public void removeManager(Manager manager) {
        managers.remove(manager.getUsername());

    }

    @Override
    public void saveManager(Manager manager) {
        managers.put(manager.getUsername(), manager);
    }

    @Override
    public Manager getManagerByUsername(String username) {
        return managers.get(username);
    }
}
