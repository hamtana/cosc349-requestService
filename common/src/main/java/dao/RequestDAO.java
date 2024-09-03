/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Request;

import java.util.Collection;

/**
 *
 * @author hamishp
 */
public interface RequestDAO {

    Collection<Request> getAllRequests();

    Collection<Request> getRequestByTenant(String username);

    Request getRequestByName(String name);

    Integer createRequest(Request request);

    void updateRequest(Request request);

    void deleteRequest(Request request);

}
