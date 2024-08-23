package domain;

import java.util.List;

public class PropertyManager {

    private Manager manager;
    private Property property;

    public PropertyManager(Manager manager, Property property) {
        this.manager = manager;
        this.property = property;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setProperty(Property property){
        this.property = property;
    }

    public Property getProperty(){
        return property;
    }

}
