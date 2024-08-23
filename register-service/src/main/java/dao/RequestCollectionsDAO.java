/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Request;
import org.checkerframework.checker.interning.qual.EqualsMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hamishp
 */
public class RequestCollectionsDAO implements RequestDAO {

    private static final HashMap<String, Request> requests = new HashMap<String, Request>();
    private static final HashMap<String, Request> requestsByTenant = new HashMap<>();

    @Override
    public List<Request> getAllRequests(){
        return new ArrayList<Request>(requests.values());
    }

    @Override
    public Request getRequestById(String id){
        return requests.get(id);
    }

    @Override
    public void createRequest(Request request){
        requests.put(request.getId(), request);
        requestsByTenant.put(request.getTenant().getId(), request);
    }

   @Override
   public void updateRequest(Request request){
        requests.put(request.getId(), request);
        requestsByTenant.put(request.getTenant().getId(), request);
    }

    @Override
    public void delete(Request request) {
        requests.remove(request.getId());
        requestsByTenant.remove(request.getTenant().getId());
    }



}
