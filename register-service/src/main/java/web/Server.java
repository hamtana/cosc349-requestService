package web;

import dao.DAOFactory;
import dao.RequestDAO;
import dao.TenantDAO;
import io.jooby.Jooby;
import io.jooby.gson.GsonModule;
import io.jooby.StatusCode;
import java.nio.file.Paths;

public class Server extends Jooby {

    private static final TenantDAO tenantDAO = DAOFactory.getTenantDAO();
    private static final RequestDAO requestDAO = DAOFactory.getRequestDAO();

    public Server(){
        install(new GsonModule());
        mount(new TenantModule(tenantDAO));

        error(StatusCode.SERVER_ERROR, (ctx, cause, code) -> {
            ctx.getRouter().getLog().error(cause.getMessage(), cause);
            ctx.send("Server error: " + cause.getMessage());
        });


    }


}

