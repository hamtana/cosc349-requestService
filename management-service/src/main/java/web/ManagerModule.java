package web;

import dao.ManagerDAO;
import dao.TenantDAO;
import domain.Manager;
import domain.Tenant;
import helpers.Argon2Helper;
import io.jooby.Jooby;
import io.jooby.StatusCode;

import java.nio.CharBuffer;

public class ManagerModule extends Jooby {

    public ManagerModule(ManagerDAO dao){

        get("/api/managers/{username}", ctx -> {
            String username = ctx.path("username").value();
            Manager manager = dao.getManagerByUsername(username);

            if (manager == null){
                return ctx.send(StatusCode.NOT_FOUND);
            } else {
                return manager;
            }
        });


        post("/api/managers", ctx -> {
            Manager manager = ctx.body().to(Manager.class);
            String password = manager.getPassword();
            CharBuffer hash = Argon2Helper.hashPasswordChar(password);
            manager.setPassword(hash.toString());
            dao.saveManager(manager);
            return ctx.send(StatusCode.CREATED);

        });


    }
}
