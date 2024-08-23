/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author hamishp
 */
public class Manager {
    private String id; 
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private List<PropertyManager> properties ;

    public Manager(String id, String firstName, String lastName, String phoneNumber, List<PropertyManager> properties) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<PropertyManager> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyManager> properties) {
        this.properties = properties;
    }
}
