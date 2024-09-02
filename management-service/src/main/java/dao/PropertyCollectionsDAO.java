package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Property;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PropertyCollectionsDAO implements PropertyDAO {

    private static final Map<String, Property> properties = new HashMap<>();
    private static final Multimap<String, Property> propertiesByManagerUsername = HashMultimap.create();

    @Override
    public void createProperty(Property property) {
        properties.put(property.getName(), property);
        propertiesByManagerUsername.put(property.getManager().getUsername(), property);
    }

    @Override
    public void updateProperty(Property property) {
        properties.put(property.getName(), property);
        propertiesByManagerUsername.put(property.getManager().getUsername(), property);
    }

    @Override
    public void deleteProperty(Property property) {
        properties.remove(property.getName());
        propertiesByManagerUsername.remove(property.getManager().getUsername(), property);
    }

    @Override
    public Property getPropertyByName(String name) {
        return properties.get(name);
    }

    @Override
    public Collection<Property> getAllProperties() {
        return properties.values();
    }

    @Override
    public Collection<Property> getPropertiesByManagerUsername(String username) {
        return propertiesByManagerUsername.get(username);
    }
}
