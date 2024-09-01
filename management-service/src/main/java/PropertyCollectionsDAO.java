import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Property;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PropertyCollectionsDAO implements PropertyDAO {

    private static final Map<Integer, Property> properties = new HashMap<>();
    private static final Multimap<Integer, Property> propertiesByManagerId = HashMultimap.create();

    @Override
    public void createProperty(Property property) {
        properties.put(property.getId(), property);
        propertiesByManagerId.put(property.getManager().getId(), property);
    }

    @Override
    public void updateProperty(Property property) {
        properties.put(property.getId(), property);
        propertiesByManagerId.put(property.getManager().getId(), property);
    }

    @Override
    public void deleteProperty(Property property) {
        properties.remove(property.getId());
        propertiesByManagerId.remove(property.getManager().getId(), property);
    }

    @Override
    public Property getPropertyById(Integer id) {
        return properties.get(id);
    }

    @Override
    public Collection<Property> getAllProperties() {
        return properties.values();
    }

    @Override
    public Collection<Property> getPropertiesByManagerId(Integer managerId) {
        return propertiesByManagerId.get(managerId);
    }
}
