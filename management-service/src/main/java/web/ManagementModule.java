package web;

import dao.ManagementDAO;
import dao.ManagerDAO;
import dao.TenantDAO;
import domain.Management;
import domain.Manager;
import domain.Tenant;
import helpers.Argon2Helper;
import io.jooby.Jooby;
import io.jooby.StatusCode;

import java.nio.CharBuffer;
import java.time.LocalDateTime;

public class ManagementModule extends Jooby {

    public ManagementModule(ManagementDAO dao){

        post("/api/management", ctx -> {
            Management management = ctx.body().to(Management.class);
            management.setDate(LocalDateTime.now());
            dao.createJob(management);
            return ctx.send(StatusCode.CREATED);
        });

    }


}
