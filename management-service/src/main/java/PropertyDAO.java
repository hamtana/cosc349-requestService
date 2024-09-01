import domain.Property;

import java.util.Collection;

public interface PropertyDAO {

    void createProperty(Property property);

    void updateProperty(Property property);

    void deleteProperty(Property property);

    Property getPropertyById(Integer id);

    Collection<Property> getAllProperties();

    Collection<Property> getPropertiesByManagerId(Integer managerId);

}
