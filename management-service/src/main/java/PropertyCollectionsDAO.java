import domain.Property;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PropertyCollectionsDAO implements PropertyDAO {

    private static final Map<Integer, Property> properties = new HashMap<>();

    @Override
    public void createProperty(Property property) {
        properties.put(property.getId(), property);
    }

    @Override
    public void updateProperty(Property property) {
        properties.put(property.getId(), property);
    }

    @Override
    public void deleteProperty(Property property) {
        properties.remove(property.getId());
    }

    @Override
    public Property getPropertyById(Integer id) {
        return properties.get(id);
    }

    @Override
    public Collection<Property> getAllProperties() {
        return properties.values();
    }
}
