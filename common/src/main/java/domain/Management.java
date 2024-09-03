package domain;

import java.time.LocalDateTime;
import java.util.Collection;

public class Management {

    private Integer workNumber;
    private String name;
    private String description;
    private Property property;
    private Request request;
    private String status;
    private transient LocalDateTime date;

    public Management() {
    }

    public Management(String name, String description, Property property, Request request, String status, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.property = property;
        this.request = request;
        this.status = status;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
