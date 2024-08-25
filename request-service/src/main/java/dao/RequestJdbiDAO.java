package dao;

import domain.Request;

import java.util.Collection;

public interface RequestJdbiDAO extends RequestDAO{

    @Override
    Collection<Request> getAllRequests();

    @Override
    Request getRequestById(String id);

    @Override
    void createRequest(Request request);

    @Override
    void updateRequest(Request request);

    @Override
    void deleteRequest(Request request);
}
