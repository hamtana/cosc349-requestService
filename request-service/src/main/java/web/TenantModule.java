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
