package web;

import dao.TenantDAO;
import helpers.Argon2Helper;
import helpers.ScryptHelper;
import io.jooby.Jooby;
import domain.Tenant;
import io.jooby.StatusCode;

import java.nio.CharBuffer;

public class TenantModule extends Jooby {

    public TenantModule(TenantDAO dao){

        get("/api/tenants/{username}", ctx -> {
            String username = ctx.path("username").value();
            Tenant tenant = dao.getTenantByUsername(username);
            if (tenant == null){
                return ctx.send(StatusCode.NOT_FOUND);
            } else {
                return tenant;
            }
        });


        post("/api/tenants", ctx -> {
            Tenant tenant = ctx.body().to(Tenant.class);
            String password = tenant.getPassword();
            CharBuffer hash = Argon2Helper.hashPasswordChar(password);
            tenant.setPassword(hash.toString());
            dao.saveTenant(tenant);
            return ctx.send(StatusCode.CREATED);

        });


    }
}
