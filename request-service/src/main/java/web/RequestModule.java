package web;

import dao.RequestDAO;
import domain.Request;
import helpers.Argon2Helper;
import io.jooby.Jooby;
import domain.Tenant;
import io.jooby.StatusCode;

import java.nio.CharBuffer;
import java.util.Collection;

public class RequestModule extends Jooby {

    public RequestModule(RequestDAO dao){

        get("/api/requests", ctx -> {
            return dao.getAllRequests();
        });

        get("/api/requests/{username}", ctx ->{
           String username = ctx.path("username").value();
           Collection<Request> requests = dao.getRequestByTenant(username);

           if (requests.isEmpty()){
               return ctx.send(StatusCode.NOT_FOUND);
           } else {
               return requests;
           }
        });

        post("/api/requests", ctx -> {
            Request request = ctx.body().to(Request.class);
            Tenant tenant = ctx.body().to(Tenant.class);
            request.setTenant(tenant);
            dao.createRequest(request);
            return ctx.send(StatusCode.CREATED);
        });




    }
}
