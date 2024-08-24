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
    private static final Multimap<String, Request> requestsByTenant = HashMultimap.create();

    //Test data to check if it visible on the website.
    @SuppressWarnings("OverridableMethodCallInContructor")
    public RequestCollectionsDAO(){
        if(requests.isEmpty()){

            //Create some dummy data
            Request request1 = new Request("0001", "Broken Toilet", "The toilet is broken", true, null, null, false);
            Request request2 = new Request("0002", "Broken Window", "The window is broken", false, null, null, false);
            Request request3 = new Request("0003", "Broken Door", "The door is broken", true, null, null, false);
            Request request4 = new Request("0004", "Broken Roof", "The roof is broken", false, null, null, false);

            requests.put(request1.getId(), request1);
            requests.put(request2.getId(), request2);
            requests.put(request3.getId(), request3);
            requests.put(request4.getId(), request4);

            requestsByTenant.put(request1.getTenant().getId(), request1);
            requestsByTenant.put(request2.getTenant().getId(), request2);
            requestsByTenant.put(request3.getTenant().getId(), request3);
            requestsByTenant.put(request4.getTenant().getId(), request4);

        }

    }

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
    public void deleteRequest(Request request) {
        requests.remove(request.getId());
        requestsByTenant.remove(request.getTenant().getId(), request);
    }



}
