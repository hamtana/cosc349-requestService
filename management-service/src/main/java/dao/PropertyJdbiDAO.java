package dao;

import domain.Property;

import java.util.Collection;

public interface PropertyJdbiDAO extends PropertyDAO {

    void createProperty(Property property);

    void updateProperty(Property property);

    void deleteProperty(Property property);

    Property getPropertyById(Integer id);

    Collection<Property> getAllProperties();

    Collection<Property> getPropertiesByManagerUsername(String username);
}
