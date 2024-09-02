package domain;

public class Management {

    private Integer workNumber;
    private String name;
    private String description;
    private Property property;
    private Request request;

    public Management() {
    }

    public Management(String name, String description, Property property, Request request) {
        this.name = name;
        this.description = description;
        this.property = property;
        this.request = request;
    }

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
