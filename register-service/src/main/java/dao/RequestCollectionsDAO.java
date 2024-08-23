/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Request;

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
    public void delete(String id) {
        requests.remove(id);
        requestsByTenant.remove(requests.get(id).getTenant().getId());
    }



}
