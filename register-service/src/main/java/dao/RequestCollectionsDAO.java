/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Request;
import java.util.HashMap;

/**
 *
 * @author hamishp
 */
public class RequestCollectionsDAO implements RequestDAO {

    private static final Multimap<String, Request> requests = HashMultimap.create();
    private static final Multimap<String, Request> requestsByTenant = HashMultimap.create();

    @Override
    public void createRequest(Request request){
        requests.put(request.getId(), request);
        requestsByTenant.put(request.getTenant().getId(), request);
    }


}
